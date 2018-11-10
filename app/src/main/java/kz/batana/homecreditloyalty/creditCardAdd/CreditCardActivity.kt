package kz.batana.homecreditloyalty.creditCardAdd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.Menu
import android.view.MenuInflater
import com.braintreepayments.cardform.view.CardForm
import kotlinx.android.synthetic.main.activity_credit_card.*
import kz.batana.homecreditloyalty.R

class CreditCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_card)
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .cardholderName(CardForm.FIELD_REQUIRED)
                .setup(this)
        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Привязка карты"
        actionBar?.apply {
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
            this.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
            this.setDisplayShowTitleEnabled(true)
        }

        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.done ->{

                    true
                }
                else->{
                    true
                }
            }
        }

        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                addCard.setBackgroundResource(R.color.primaryLightColor)
                addCard.isEnabled=true
            }
            else{
                addCard.setBackgroundResource(android.R.drawable.btn_default)
                addCard.isEnabled=false
            }
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        var menuInflater: MenuInflater = menuInflater
//        menuInflater.inflate(R.menu.registration_menu,menu)
//        return true
//    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
