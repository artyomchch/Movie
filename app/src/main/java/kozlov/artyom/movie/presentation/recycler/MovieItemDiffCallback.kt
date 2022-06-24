package kozlov.artyom.movie.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import kozlov.artyom.movie.domain.entity.MovieItem


class MovieItemDiffCallback : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem == newItem
    }

}
