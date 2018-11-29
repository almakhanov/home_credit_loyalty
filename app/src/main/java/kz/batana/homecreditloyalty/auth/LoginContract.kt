package kz.batana.homecreditloyalty.auth

import android.annotation.SuppressLint
import io.reactivex.Observable
import kz.batana.homecreditloyalty.core.util.IPresenter
import kz.batana.homecreditloyalty.core.util.IView
import kz.batana.homecreditloyalty.entity.Customer

interface LoginContract{

    interface LoginView: IView<LoginPresenter> {
        fun showProgress()
        fun hideProgress()
        fun success(user: Customer)
        fun showError(text: String)
    }

    interface LoginPresenter: IPresenter<LoginView> {
        fun signIn(email:String, password:String)

        @SuppressLint(value = { "CheckResult" })
        fun signInLocallY(email: String, password: String)
    }

    interface LoginRepository{
        fun login(email:String,password:String):Observable<LoginResponse>
        fun loginLocallY(): Observable<List<Customer>>
    }
}