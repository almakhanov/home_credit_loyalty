package kz.batana.homecreditloyalty.task

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_task_detail.*
import kotlinx.android.synthetic.main.fragment_report.*
import kz.batana.homecreditloyalty.Constants
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.autoPayment.AutoPaymentActivity
import kz.batana.homecreditloyalty.core.util.Logger
import kz.batana.homecreditloyalty.entity.Task
import kz.batana.homecreditloyalty.mainMenu.MainMenuActivity
import kz.batana.homecreditloyalty.tasks_tabs.TasksService
import org.koin.android.ext.android.inject

class TaskDetailActivity : AppCompatActivity() {

    private val sharedPref: SharedPreferences by inject()
    lateinit var task: Task
    private val taskService: TasksService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        task = intent.getParcelableExtra(Constants.TASK) as Task

        detailTaskVTitleValue.text = task.title
        detailTaskVdescValue.text = task.description
        detailTaskVdateValue.text = task.date
        detailTaskVbonusValue.text = task.bonus.toString()

        Logger.msg("accepted==", task.expiredDate!!.toLowerCase())
        goToTask.setOnClickListener{
            Logger.msg("accepted", task.expiredDate!!.toLowerCase())
            when(task.expiredDate!!.toLowerCase()){
                "tel" -> {
                    startActivity(Intent(this,AutoPaymentActivity::class.java))
                    finish()
                }
                "card" -> {

                }
                "transaction" -> {

                }
                "bug" -> {
                    openBugDialog()
                }

                "evaluate" -> {
                    openEvaluateDialog()
                }
                "recommend" -> {
                    openRecommendDialog()
                }
                "feedback" -> {
                    openFeedbackDialog()
                }
                "question" -> {
                    openQuestionDialog()
                }
            }
        }


        //Toolbar
        setSupportActionBar(toolbar_waybill_task_details)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
            this.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
            this.title = "Детали задачи"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun openBugDialog(){
        val dialog: AlertDialog
        val viewDialog: View = layoutInflater.inflate(R.layout.custom_bug_dialog_layout, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(viewDialog)
        val dialogEditText = viewDialog.findViewById<View>(R.id.edit_text_custom_dialog_put_fio) as EditText
        builder.setMessage("Сообщение о баге!")
        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> {
                    taskService.addPoint(MainMenuActivity.user!!.id, task.id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                MainMenuActivity.user = it
                                startActivity(Intent(this, MainMenuActivity::class.java))
                            },{

                            })

                    toast("Спасибо что помогаете нам!")
                }
                DialogInterface.BUTTON_NEUTRAL -> {
                }
            }
        }

        builder.setPositiveButton("Сообщить", dialogClickListener)
        builder.setNeutralButton("Отмена", dialogClickListener)

        dialog = builder.create()
        dialog.show()
        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.isEnabled = false
        dialogEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if(dialogEditText.text.isEmpty()){
                    if(positiveButton.isEnabled){
                        positiveButton.isEnabled = false
                    }
                } else {
                    if(!positiveButton.isEnabled){
                        positiveButton.isEnabled = true
                    }
                }
            }
        })
    }

    private fun openEvaluateDialog(){
        val dialog: AlertDialog
        val viewDialog: View = layoutInflater.inflate(R.layout.custom_evaluate_dialog_layout, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(viewDialog)
        val dialogEditText = viewDialog.findViewById<View>(R.id.rb_vvm) as RatingBar
        builder.setMessage("Оцените приложение")
        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> {
                    Logger.msg("accepted", dialogEditText.rating)
                    toast("Спасибо за ваш отзыв")
                    layoutEvaluateBox.visibility  = View.GONE
                    sharedPref.edit()
                            .putInt(Constants.EVALUATE, 1)
                            .apply()
                }
                DialogInterface.BUTTON_NEUTRAL -> {
                }
            }
        }

        builder.setPositiveButton("Сообщить", dialogClickListener)
        builder.setNeutralButton("Отмена", dialogClickListener)

        dialog = builder.create()
        dialog.show()
        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.isEnabled = false
        dialogEditText.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            positiveButton.isEnabled = dialogEditText.rating != 0.0F
        }
    }

    private fun openRecommendDialog(){
        val dialog: AlertDialog
        val viewDialog: View = layoutInflater.inflate(R.layout.custom_recommend_dialog_layout, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(viewDialog)
        val dialogEditText = viewDialog.findViewById<View>(R.id.edit_text_custom_dialog_put_fio) as EditText
        builder.setMessage("Хотите улучшить наш сервис?")
        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> {
                    //TODO
                    toast("Спасибо что помогаете нам!")
                }
                DialogInterface.BUTTON_NEUTRAL -> {
                }
            }
        }

        builder.setPositiveButton("Сообщить", dialogClickListener)
        builder.setNeutralButton("Отмена", dialogClickListener)

        dialog = builder.create()
        dialog.show()
        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.isEnabled = false
        dialogEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if(dialogEditText.text.isEmpty()){
                    if(positiveButton.isEnabled){
                        positiveButton.isEnabled = false
                    }
                } else {
                    if(!positiveButton.isEnabled){
                        positiveButton.isEnabled = true
                    }
                }
            }
        })
    }

    private fun openFeedbackDialog(){
        val dialog: AlertDialog
        val viewDialog: View = layoutInflater.inflate(R.layout.custom_recommend_dialog_layout, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(viewDialog)
        val dialogEditText = viewDialog.findViewById<View>(R.id.edit_text_custom_dialog_put_fio) as EditText
        dialogEditText.hint = "Оцените нас"
        builder.setMessage("Оставьте отзыв")
        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> {
                    //TODO
                    toast("Спасибо за ваш отзыв!")
                    layoutFeefbackBox.visibility  = View.GONE
                    sharedPref.edit()
                            .putInt(Constants.FEEDBACK, 1)
                            .apply()
                }
                DialogInterface.BUTTON_NEUTRAL -> {
                }
            }
        }

        builder.setPositiveButton("Сообщить", dialogClickListener)
        builder.setNeutralButton("Отмена", dialogClickListener)

        dialog = builder.create()
        dialog.show()
        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.isEnabled = false
        dialogEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if(dialogEditText.text.isEmpty()){
                    if(positiveButton.isEnabled){
                        positiveButton.isEnabled = false
                    }
                } else {
                    if(!positiveButton.isEnabled){
                        positiveButton.isEnabled = true
                    }
                }
            }
        })
    }


    private fun openQuestionDialog(){
        val dialog: AlertDialog
        val viewDialog: View = layoutInflater.inflate(R.layout.custom_recommend_dialog_layout, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(viewDialog)
        val dialogEditText = viewDialog.findViewById<View>(R.id.edit_text_custom_dialog_put_fio) as EditText
        dialogEditText.hint = "У вас есть вопросы?"
        builder.setMessage("Напишите о вашей проблеме")
        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> {
                    //TODO
                    toast("Спасибо, скоро ответим!")
                }
                DialogInterface.BUTTON_NEUTRAL -> {
                }
            }
        }

        builder.setPositiveButton("Сообщить", dialogClickListener)
        builder.setNeutralButton("Отмена", dialogClickListener)

        dialog = builder.create()
        dialog.show()
        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.isEnabled = false
        dialogEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if(dialogEditText.text.isEmpty()){
                    if(positiveButton.isEnabled){
                        positiveButton.isEnabled = false
                    }
                } else {
                    if(!positiveButton.isEnabled){
                        positiveButton.isEnabled = true
                    }
                }
            }
        })
    }


    private fun toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
