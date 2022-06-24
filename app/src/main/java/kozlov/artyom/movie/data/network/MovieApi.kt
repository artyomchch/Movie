package kozlov.artyom.movie.data.network

import kozlov.artyom.movie.BuildConfig
import kozlov.artyom.movie.data.network.pojo.MovieDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {


    @GET("all.json")
    suspend fun getPostPaging(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_PAGE) page: Int = PAGE,
    ): MovieDto


    companion object {
        private const val QUERY_PARAM_API_KEY = "api-key"
        private const val QUERY_PARAM_PAGE = "offset"


        private const val API_KEY = BuildConfig.API_KEY
        private const val PAGE = 0

    }
}