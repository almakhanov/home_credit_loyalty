package kz.batana.homecreditloyalty

import android.app.Application
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
    }

    companion object {
        @JvmStatic var instance: App? = null
            private set
    }
}