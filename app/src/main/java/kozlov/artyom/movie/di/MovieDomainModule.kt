package kozlov.artyom.movie.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import kozlov.artyom.movie.data.network.MovieApi
import kozlov.artyom.movie.data.network.RetrofitInstance
import kozlov.artyom.movie.data.repository.MovieListRepositoryImpl
import kozlov.artyom.movie.domain.repository.MovieListRepository

@Module
interface MovieDomainModule {

    @ApplicationScope
    @Binds
    fun bindNewsListRepository(impl: MovieListRepositoryImpl): MovieListRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideAppInternet(): MovieApi {
            return RetrofitInstance.api
        }
    }
}