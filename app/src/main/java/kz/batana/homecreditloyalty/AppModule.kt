package kz.batana.homecreditloyalty

import kz.batana.homecreditloyalty.auth.authModule
import kz.batana.homecreditloyalty.core.coreModule
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModules: List<Module>
    get() = listOf(
            authModule,
            coreModule,
            singletons
    )

val singletons = module {
//    Logger.msg("accepted",Constants.URL )
//    single { createService<StoreService>(get(), Constants.URL) }
//    single { createService<HistoryService>(get(), Constants.URL) }
//    single { createService<ClubService>(get(), Constants.URL) }
}