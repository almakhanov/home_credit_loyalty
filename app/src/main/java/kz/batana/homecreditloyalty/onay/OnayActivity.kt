package kz.batana.homecreditloyalty.onay

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_onay.*
import kz.batana.homecreditloyalty.R
import java.lang.StringBuilder

class OnayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onay)
        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = " Пополнение Онай"
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
        student.setOnClickListener {
            student.setBackgroundResource(R.color.colorBlue)
            proezdnoi.setBackgroundResource(R.color.colorType)
            pupil.setBackgroundResource(R.color.colorType)
            simple.setBackgroundResource(R.color.colorType)

        }
        proezdnoi.setOnClickListener {
            proezdnoi.setBackgroundResource(R.color.colorBlue)
            student.setBackgroundResource(R.color.colorType)
            pupil.setBackgroundResource(R.color.colorType)
            simple.setBackgroundResource(R.color.colorType)

        }
        simple.setOnClickListener {
            simple.setBackgroundResource(R.color.colorBlue)
            proezdnoi.setBackgroundResource(R.color.colorType)
            pupil.setBackgroundResource(R.color.colorType)
            student.setBackgroundResource(R.color.colorType)

        }
        pupil.setOnClickListener {
            pupil.setBackgroundResource(R.color.colorBlue)
            proezdnoi.setBackgroundResource(R.color.colorType)
            student.setBackgroundResource(R.color.colorType)
            simple.setBackgroundResource(R.color.colorType)

        }
        onay.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length==5){
                    onay.setText(StringBuilder(s).insert(s.length-1," ").toString())
                    onay.setSelection(onay.text.length)
                }
            }

        })
        bonus.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()){
                    if (s!!.toString().toInt()>1000){
                        bonus.startAnimation(AnimationUtils.loadAnimation(this@OnayActivity,R.anim.shake))
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
