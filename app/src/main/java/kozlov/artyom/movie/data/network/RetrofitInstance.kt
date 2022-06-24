package kozlov.artyom.movie.data.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {




    private val retrofit by lazy {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        val moshi = Moshi.Builder().build()

        val moshiConverterFactory = MoshiConverterFactory.create(moshi)

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    val api: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
}