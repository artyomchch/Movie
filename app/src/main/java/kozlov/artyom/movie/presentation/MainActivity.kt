package kozlov.artyom.movie.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kozlov.artyom.movie.databinding.ActivityMainBinding
import kozlov.artyom.movie.presentation.recycler.LoaderStateAdapter
import kozlov.artyom.movie.presentation.recycler.MoviePagingAdapter
import kozlov.artyom.movie.presentation.recycler.SpaceItemDecoration
import kozlov.artyom.movie.presentation.recycler.TryAgainAction
import kozlov.artyom.movie.utils.MyApp
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var moviePagingAdapter: MoviePagingAdapter
    private lateinit var loaderStateAdapter: LoaderStateAdapter
    private lateinit var mainLoadStateHolder: LoaderStateAdapter.LoaderViewHolder

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[(MainActivityViewModel::class.java)]
    }

    private val component by lazy {
        (application as MyApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        setContentView(binding.root)

    }

    private fun setupRecyclerView() {
        moviePagingAdapter = MoviePagingAdapter()
        val tryAgainAction: TryAgainAction = { moviePagingAdapter.retry() }
        loaderStateAdapter = LoaderStateAdapter(tryAgainAction)
        binding.movieRecycler.apply {
            adapter = moviePagingAdapter.withLoadStateFooter(loaderStateAdapter)
            addItemDecoration(SpaceItemDecoration())
        }

        mainLoadStateHolder = LoaderStateAdapter.LoaderViewHolder(
            binding.loadStateView,
            binding.shimmerLayout,
            tryAgainAction
        )

        observeMovie()
        observeLoadState()

    }

    private fun observeMovie() {
        lifecycleScope.launch {
            viewModel.newsFlow.collectLatest { pagingData ->
                moviePagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun observeLoadState() {
        lifecycleScope.launch {
            moviePagingAdapter.loadStateFlow.collectLatest { state ->
                mainLoadStateHolder.bind(state.refresh)
                if (state.refresh is LoadState.Error) disableToolBarScrolling()
                if (state.refresh is LoadState.Loading) enableToolBarScrolling()
            }
        }
    }

    private fun disableToolBarScrolling() {
        val params = binding.mainCollapsing.layoutParams as AppBarLayout.LayoutParams
        params.scrollFlags = 0
    }

    private fun enableToolBarScrolling() {
        val params = binding.mainCollapsing.layoutParams as AppBarLayout.LayoutParams
        params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
        binding.mainAppbar.setExpanded(true)
    }


}