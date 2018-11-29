package kz.batana.homecreditloyalty.telephone

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_balance_add.*
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.mainMenu.MainMenuActivity
import kz.batana.homecreditloyalty.tasks_tabs.TasksService
import org.koin.android.ext.android.inject

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
        setSupportActionBar(toolbarbalance)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Пополнение баланса"
        actionBar?.apply {
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
            this.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
            this.setDisplayShowTitleEnabled(true)
        }

        toolbarbalance.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.done ->{

                    true
                }
                else->{
                    true
                }
            }
        }

        commit.setOnClickListener{
            Toast.makeText(this, "Спасибо за платеж!", Toast.LENGTH_SHORT).show()
            Thread.sleep(500)
            MainMenuActivity.user!!.current_points -= bonus.text.toString().toInt()
            startActivity(Intent(this, MainMenuActivity::class.java))
            finish()
        }
        bonus.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()){
                    if (s!!.toString().toInt()>MainMenuActivity.user!!.current_points){
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

        tel_balance.text = MainMenuActivity.user!!.current_points.toString()



    }    private val serice: TasksService by inject()

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
