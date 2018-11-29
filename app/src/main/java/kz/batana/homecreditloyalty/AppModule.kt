package kz.batana.homecreditloyalty

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import kz.batana.homecreditloyalty.auth.authModule
import kz.batana.homecreditloyalty.core.coreModule
import kz.batana.homecreditloyalty.core.createService
import kz.batana.homecreditloyalty.history.HistoryService
import kz.batana.homecreditloyalty.local_storage.AppLocalDatabase
import kz.batana.homecreditloyalty.task.taskModule
import kz.batana.homecreditloyalty.tasks_tabs.TasksService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModules: List<Module>
    get() = listOf(
            authModule,
            coreModule,
            singletons,
            taskModule
    )

val singletons = module {
    single { createSharedPrefs(androidContext()) }
    single { createService<TasksService>(get()) }
    single { createService<HistoryService>(get()) }
    single { createLocalStorage(androidContext())}
}


internal fun createSharedPrefs(context: Context) : SharedPreferences {
    return context.applicationContext.getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
}


fun createLocalStorage(context:Context) : AppLocalDatabase {
    return Room.databaseBuilder(context, AppLocalDatabase::class.java,"home_credit_loyalty_db").build()
}