package kz.batana.homecreditloyalty.auth

import kz.batana.homecreditloyalty.core.createService
import org.koin.dsl.module.module

val authModule = module {
    factory { LoginPresenter(get()) as LoginContract.LoginPresenter }
    factory { LoginRepository(get()) as LoginContract.LoginRepository }
    single { createService<LoginService>(get()) }
}