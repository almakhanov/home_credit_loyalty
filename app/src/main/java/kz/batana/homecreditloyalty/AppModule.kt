package kz.batana.homecreditloyalty

import android.content.Context
import android.content.SharedPreferences
import kz.batana.homecreditloyalty.auth.authModule
import kz.batana.homecreditloyalty.core.coreModule
import kz.batana.homecreditloyalty.core.createService
import kz.batana.homecreditloyalty.tasks_tabs.TasksService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModules: List<Module>
    get() = listOf(
            authModule,
            coreModule,
            singletons
    )

val singletons = module {
    single { createSharedPrefs(androidContext()) }
//    Logger.msg("accepted",Constants.URL )
    single { createService<TasksService>(get()) }
//    single { createService<HistoryService>(get(), Constants.URL) }
//    single { createService<ClubService>(get(), Constants.URL) }
}


internal fun createSharedPrefs(context: Context) : SharedPreferences {
    return context.applicationContext.getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
}