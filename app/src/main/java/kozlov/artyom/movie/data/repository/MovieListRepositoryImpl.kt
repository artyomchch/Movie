package kozlov.artyom.movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kozlov.artyom.movie.data.mapper.MovieItemMapper
import kozlov.artyom.movie.data.network.MovieApi
import kozlov.artyom.movie.data.pagination.MoviePagingSource
import kozlov.artyom.movie.domain.entity.MovieItem
import kozlov.artyom.movie.domain.repository.MovieListRepository
import javax.inject.Inject


class MovieListRepositoryImpl @Inject constructor(
    private val mapper: MovieItemMapper,
    private val retrofit: MovieApi
) : MovieListRepository {


    override fun getPagedNews(): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(retrofit, mapper) }
        ).flow
    }

    fun refreshPagedNews(): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(retrofit, mapper) }
        ).flow
    }


}