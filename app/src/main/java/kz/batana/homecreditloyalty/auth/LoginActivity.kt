package kz.batana.homecreditloyalty.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_login.*
import kz.batana.homecreditloyalty.Constants
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.entity.Customer
import kz.batana.homecreditloyalty.mainMenu.MainMenuActivity
import kz.batana.homecreditloyalty.registration.RegistrationActivity
import org.koin.android.ext.android.inject
import java.io.Serializable

class LoginActivity : AppCompatActivity(),LoginContract.LoginView {
    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        loginSignInButton.text = ""
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
        loginSignInButton.text = "Войти"
    }

    override fun success(user: Customer) {
        val intent = Intent(this, MainMenuActivity::class.java)
        intent.putExtra("user", user as Serializable)
        startActivity(intent)
        finish()
    }

    override fun showError(text:String) {
        Toast.makeText(this,text , Toast.LENGTH_LONG).show()
    }

    private val sharedPref: SharedPreferences by inject()
    override val presenter: LoginContract.LoginPresenter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.attachView(this)

        if(sharedPref.getInt(Constants.DOWNLOADED,0) == 0){
            sharedPref.edit().putInt(Constants.DOWNLOADED, 1).apply()

        }

        loginRegisterButton.setOnClickListener{
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("token accepted", it.result?.token)
                    }
                }
        loginSignInButton.setOnClickListener { _ ->
                        presenter.signIn(loginEmailEditText.text.toString(), loginPasswordEditText.text.toString())
                    }
        }
}
