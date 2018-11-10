package kz.batana.homecreditloyalty.converter

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_converter.*
import kz.batana.homecreditloyalty.R

class ConverterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Конвертация бонусов"
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

        fromBonus.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()){
                    if (s!!.toString().toInt()>1000){
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
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
