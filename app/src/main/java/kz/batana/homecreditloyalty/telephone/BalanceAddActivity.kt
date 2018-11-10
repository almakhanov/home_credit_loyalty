package kz.batana.homecreditloyalty.telephone

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_balance_add.*
import kz.batana.homecreditloyalty.R

class BalanceAddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance_add)
        phoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        phoneNumber.addTextChangedListener(object: TextWatcher {
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
        bonus.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()){
                    if (s!!.toString().toInt()>1000){
                        bonus.startAnimation(AnimationUtils.loadAnimation(this@BalanceAddActivity,R.anim.shake))
                        bonus.setTextColor(Color.RED)
                        commit.setBackgroundResource(R.color.colorType)
                        commit.isEnabled=false
                    }
                    else{
                        if(s.toString().toInt()>0)
                            bonus.setTextColor(Color.BLACK)
                        commit.setBackgroundResource(R.color.colorPrimary)
                        commit.isEnabled= true
                    }
                }
                else{
                }
            }

        })


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
