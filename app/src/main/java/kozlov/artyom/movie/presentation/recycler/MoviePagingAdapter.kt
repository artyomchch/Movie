package kozlov.artyom.movie.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import kozlov.artyom.movie.R
import kozlov.artyom.movie.databinding.MovieItemBinding
import kozlov.artyom.movie.domain.entity.MovieItem
import javax.inject.Inject


class MoviePagingAdapter : PagingDataAdapter<MovieItem, MovieItemViewHolder>(MovieItemDiffCallback()) {


    override fun onBindViewHolder(viewHolder: MovieItemViewHolder, position: Int) {
        val movieItem = getItem(position) ?: return
        with(viewHolder.binding) {
            Glide.with(root)
                .load(movieItem.imageUrl)
                .placeholder(R.drawable.ic_movie_filter)
                .centerCrop()
                .into(moviePic)

            title.text = movieItem.title
            description.text = movieItem.description

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieItemViewHolder(binding)
    }
}