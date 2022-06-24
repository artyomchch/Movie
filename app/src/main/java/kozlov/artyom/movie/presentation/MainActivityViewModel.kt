package kozlov.artyom.movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kozlov.artyom.movie.domain.entity.MovieItem
import kozlov.artyom.movie.domain.usecases.GetMoviePagingListUseCase
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    getMoviePagingListUseCase: GetMoviePagingListUseCase
) : ViewModel() {

    var newsFlow: Flow<PagingData<MovieItem>> = getMoviePagingListUseCase.invoke().cachedIn(viewModelScope)


    override fun onCleared() {
        newsFlow.cancellable()
        super.onCleared()
    }

}