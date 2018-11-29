package kz.batana.homecreditloyalty.onay

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_onay.*
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.mainMenu.MainMenuActivity
import kz.batana.homecreditloyalty.tasks_tabs.TasksService
import org.koin.android.ext.android.inject
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
                    if (s!!.toString().toInt()>MainMenuActivity.user!!.current_points){
                        bonus.startAnimation(AnimationUtils.loadAnimation(this@OnayActivity,R.anim.shake))
                        bonus.setTextColor(Color.RED)
                        commitOnay.setBackgroundResource(R.color.colorType)
                        commitOnay.isEnabled=false
                    }
                    else{
                        if(s.toString().toInt()>0)
                            bonus.setTextColor(Color.BLACK)
                        commitOnay.setBackgroundResource(R.color.colorPrimary)
                        commitOnay.isEnabled= true
                    }
                }
                else{
                }
            }

        })
            onay_balance.text = MainMenuActivity.user!!.current_points.toString()
        commitOnay.setOnClickListener{ view ->
            serice.pay(MainMenuActivity.user!!.id, bonus.text.toString().toInt())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Toast.makeText(this, "Спасибо за платеж!", Toast.LENGTH_SHORT).show()
                        Thread.sleep(500)
                        MainMenuActivity.user!!.current_points -= bonus.text.toString().toInt()
                        startActivity(Intent(this, MainMenuActivity::class.java))
                        finish()
                    },{

                    })
        }


    }
    private val serice: TasksService by inject()
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
