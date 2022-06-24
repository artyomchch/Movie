package kozlov.artyom.movie.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kozlov.artyom.movie.domain.entity.MovieItem


interface MovieListRepository {

    fun getPagedNews(): Flow<PagingData<MovieItem>>
}