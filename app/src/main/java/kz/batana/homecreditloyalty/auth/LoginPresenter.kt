package kz.batana.homecreditloyalty.auth

import kz.batana.homecreditloyalty.core.util.BasePresenter

class LoginPresenter(private val repository: LoginContract.LoginRepository): LoginContract.LoginPresenter,
                    BasePresenter<LoginContract.LoginView>(){
    override fun viewIsReady() {

    }

    override fun destroy() {

    }

}