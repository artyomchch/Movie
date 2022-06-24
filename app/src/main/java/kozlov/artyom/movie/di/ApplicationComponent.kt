package kozlov.artyom.movie.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import kozlov.artyom.movie.presentation.MainActivity

@ApplicationScope
@Component(
    modules = [ViewModelModule::class, MovieDomainModule::class]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }

}