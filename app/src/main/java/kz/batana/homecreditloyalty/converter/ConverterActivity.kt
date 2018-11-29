package kz.batana.homecreditloyalty.converter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_converter.*
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.mainMenu.MainMenuActivity
import kz.batana.homecreditloyalty.tasks_tabs.TasksService
import org.koin.android.ext.android.inject

class ConverterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
        setSupportActionBar(toolbarTELE)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Конвертация бонусов"
        actionBar?.apply {
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
            this.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
            this.setDisplayShowTitleEnabled(true)
        }

        toolbarTELE.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.done ->{

                    true
                }
                else->{
                    true
                }
            }
        }

        perevo_balance.text = MainMenuActivity.user!!.current_points.toString()

        coomitBtn.setOnClickListener{
            Toast.makeText(this, "Спасибо за платеж!", Toast.LENGTH_SHORT).show()
            Thread.sleep(500)
            MainMenuActivity.user!!.current_points -= fromBonus.text.toString().toInt()
            startActivity(Intent(this, MainMenuActivity::class.java))
            finish()
        }

        fromBonus.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()){
                    if (s!!.toString().toInt()>MainMenuActivity.user!!.current_points){
                        fromBonus.startAnimation(AnimationUtils.loadAnimation(this@ConverterActivity,R.anim.shake))
                        fromBonus.setTextColor(Color.RED)
                        toMoney.setText("Недостаточно бонусов", TextView.BufferType.EDITABLE)
                    }
                    else{
                    if(s!!.toString().toInt()>0)
                    fromBonus.setTextColor(Color.BLACK)
                    var converted = s.toString().toInt()*5
                    toMoney.setText(converted.toString(), TextView.BufferType.EDITABLE)
                    }
                }
                else{
                    toMoney.setText("", TextView.BufferType.EDITABLE)
                }
            }

        })


    }

    private val serice: TasksService by inject()
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
