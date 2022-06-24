package kozlov.artyom.movie.presentation.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import kozlov.artyom.movie.databinding.ItemLoaderBinding


typealias TryAgainAction = () -> Unit

class LoaderStateAdapter(
    private val tryAgainAction: TryAgainAction
) : LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {


    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val binding = ItemLoaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoaderViewHolder(binding, null, tryAgainAction)
    }


    class LoaderViewHolder(
        private val binding: ItemLoaderBinding, private val shimmerLayout: ShimmerFrameLayout?, private val tryAgainAction: TryAgainAction
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.tryAgainButton.setOnClickListener { tryAgainAction() }
        }

        fun bind(loadState: LoadState) = with(binding) {

            Log.d("state", "bind: $loadState")
            animationView.isVisible = loadState is LoadState.Error
            messageTextView.isVisible = loadState is LoadState.Error
            tryAgainButton.isVisible = loadState is LoadState.Error


            if (shimmerLayout != null) {
                shimmerLayout.isVisible = loadState is LoadState.Loading
                progressBar.isVisible = false
            } else {
                progressBar.isVisible = loadState is LoadState.Loading
                animationView.isVisible = false

            }
        }

    }
}
