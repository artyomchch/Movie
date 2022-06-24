package kozlov.artyom.movie.utils

import android.app.Application
import kozlov.artyom.movie.di.DaggerApplicationComponent

class MyApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }

}