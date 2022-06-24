package kozlov.artyom.movie.domain.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kozlov.artyom.movie.domain.entity.MovieItem
import kozlov.artyom.movie.domain.repository.MovieListRepository
import javax.inject.Inject

class GetMoviePagingListUseCase @Inject constructor(private val newsListRepository: MovieListRepository) {

    operator fun invoke(): Flow<PagingData<MovieItem>> = newsListRepository.getPagedNews()

}