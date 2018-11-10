package kz.batana.homecreditloyalty.report


import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_report.*
import kz.batana.homecreditloyalty.Constants

import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.core.util.Logger
import org.koin.android.ext.android.inject

class ReportFragment : Fragment() {

    private val sharedPref: SharedPreferences by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(sharedPref.getInt(Constants.EVALUATE, 0) != 0){
            layoutEvaluateBox.visibility  = View.GONE
        }

        if(sharedPref.getInt(Constants.FEEDBACK, 0) != 0){
            layoutFeefbackBox.visibility  = View.GONE
        }


        layoutReportBugBox.setOnClickListener{
            openBugDialog()
        }

        layoutEvaluateBox.setOnClickListener{
            openEvaluateDialog()
        }

        layoutRecommendBox.setOnClickListener{
            openRecommendDialog()
        }

        layoutFeefbackBox.setOnClickListener{
            openFeedbackDialog()
        }

        layoutQuestionBox.setOnClickListener{
            openQuestionDialog()
        }
    }




    private fun openBugDialog(){
        val dialog: AlertDialog
        val viewDialog: View = layoutInflater.inflate(R.layout.custom_bug_dialog_layout, null)
        val builder = AlertDialog.Builder(activity!!)
        builder.setView(viewDialog)
        val dialogEditText = viewDialog.findViewById<View>(R.id.edit_text_custom_dialog_put_fio) as EditText
        builder.setMessage("Сообщение о баге!")
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

    private fun openEvaluateDialog(){
        val dialog: AlertDialog
        val viewDialog: View = layoutInflater.inflate(R.layout.custom_evaluate_dialog_layout, null)
        val builder = AlertDialog.Builder(activity!!)
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
        val builder = AlertDialog.Builder(activity!!)
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
        val builder = AlertDialog.Builder(activity!!)
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
        val builder = AlertDialog.Builder(activity!!)
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
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }


}
