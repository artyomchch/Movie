package kozlov.artyom.movie.data.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kozlov.artyom.movie.data.mapper.MovieItemMapper
import kozlov.artyom.movie.data.network.MovieApi
import kozlov.artyom.movie.domain.entity.MovieItem


class MoviePagingSource(
    private val loader: MovieApi,
    private val mapper: MovieItemMapper,
) : PagingSource<Int, MovieItem>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val pageIndex = params.key ?: START_COUNT
        Log.d("page", "load:  $pageIndex")

        return try {
            val news = mapper.mapListNetworkModelToListEntityNews(loader.getPostPaging(page = pageIndex).results)
            Log.d("page", "load:  $news")
            return LoadResult.Page(
                data = news,
                prevKey = if (pageIndex == PAGE_SIZE) null else pageIndex - PAGE_SIZE,
                nextKey = if (news.isEmpty()) null else if (pageIndex == PAGE_SIZE) pageIndex + PAGE_SIZE* FIRST_SIZE_PACK_LOAD else pageIndex + PAGE_SIZE
            )

        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(20)
    }

    companion object {
        private const val PAGE_SIZE = 20
        private const val START_COUNT = 1
        private const val FIRST_SIZE_PACK_LOAD = 3
    }
}

