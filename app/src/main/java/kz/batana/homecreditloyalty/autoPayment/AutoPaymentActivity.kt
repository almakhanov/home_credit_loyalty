package kz.batana.homecreditloyalty.autoPayment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_auto_payment.*
import kz.batana.homecreditloyalty.R

class AutoPaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_payment)
        phoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        phoneNumber.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s!!.length>2){
                    Log.d("s1",s[1].toString())
                    Log.d("s2",s[2].toString())
                    if ((s!![1].toString().equals("0") && s[2].toString().equals("5")) || (s!![1].toString().equals("7") && s[2].toString().equals("7")) ){
                        operator.visibility = View.VISIBLE
                        operator.setImageResource(R.drawable.beeline)
                    }
                    if ((s!![1].toString().equals("0") && s[2].toString().equals("7")) || (s!![1].toString().equals("4") && s[2].toString().equals("7")) ){
                        operator.visibility = View.VISIBLE
                        operator.setImageResource(R.drawable.tele)
                    }
                    if ((s!![1].toString().equals("0") && s[2].toString().equals("1")) || (s!![1].toString().equals("0") && s[2].toString().equals("2")) ){
                        operator.visibility = View.VISIBLE
                        operator.setImageResource(R.drawable.activ)
                    }
                }
                else{
                    operator.visibility = View.INVISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Авто платеж"
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

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
