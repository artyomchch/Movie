package kozlov.artyom.movie.data.mapper

import kozlov.artyom.movie.data.network.pojo.Result
import kozlov.artyom.movie.domain.entity.MovieItem
import javax.inject.Inject

class MovieItemMapper @Inject constructor() {


    private fun mapNetworkModelToEntityNews(information: Result) = MovieItem(
        imageUrl = information.multimedia.src,
        title = information.display_title,
        description = information.summary_short,
    )

    fun mapListNetworkModelToListEntityNews(list: List<Result>) =
        list.map {
            mapNetworkModelToEntityNews(it)
        }


}