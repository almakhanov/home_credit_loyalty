package kz.batana.homecreditloyalty.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import kz.batana.homecreditloyalty.Constants
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.entity.Customer
import kz.batana.homecreditloyalty.mainMenu.MainMenuActivity
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity(),LoginContract.LoginView {
    private val sharedPref: SharedPreferences by inject()
    override val presenter: LoginContract.LoginPresenter by inject()
    private val service: LoginService by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.attachView(this)

        if(sharedPref.getInt(Constants.DOWNLOADED,0) == 0){
            sharedPref.edit().putInt(Constants.DOWNLOADED, 1).apply()
            //TODO
        }


        loginSignInButton.setOnClickListener { _ ->

            progressBar.visibility= View.VISIBLE
            loginSignInButton.text = ""
            service.authorize(loginEmailEditText.text.toString(),loginPasswordEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (it.code ==0){
                            val user: Customer = it.user!!
                            val intent = Intent(this, MainMenuActivity::class.java)
                            intent.putExtra("user",user as Parcelable)
                            startActivity(intent)
                            finish()
                        }else{
                            progressBar.visibility= View.GONE
                            loginSignInButton.text = "Войти"
                        }
                    }
        }
    }
}
