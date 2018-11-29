package kz.batana.homecreditloyalty.auth

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.homecreditloyalty.entity.Customer
import kz.batana.homecreditloyalty.local_storage.AppLocalDatabase

class LoginRepository(private val remoteService:LoginService,
                      private val localStorage: AppLocalDatabase):LoginContract.LoginRepository {
    override fun login(email: String, password: String):Observable<LoginResponse> {
        return remoteService.authorize(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun loginLocallY():Observable<List<Customer>>{
        return localStorage.
                customerDao()
                .get()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}