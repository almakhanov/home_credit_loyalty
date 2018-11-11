package kz.batana.homecreditloyalty.auth

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import kz.batana.homecreditloyalty.R
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
