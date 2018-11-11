package kz.batana.homecreditloyalty.task

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.tasks_fragment.*
import kz.batana.homecreditloyalty.App
import kz.batana.homecreditloyalty.Constants
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.core.util.Logger
import kz.batana.homecreditloyalty.entity.Task
import kz.batana.homecreditloyalty.mainMenu.MainMenuActivity
import kz.batana.homecreditloyalty.tasks_tabs.TasksService
import org.koin.android.ext.android.inject


@Suppress("DEPRECATION")
class MainFragment: Fragment(), CurrentTasksAdapter.OnItemClickListener {

    private lateinit var currentTasksAdapter: CurrentTasksAdapter
    private val service: TasksService by inject()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tasks_fragment,container,false)
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.showProgress(activity!!)

        progressBarLevel.visibility = View.VISIBLE
        progressBarLevel.progress = 0
        progressBarLevel.max = 200
        progressBarLevel.progressDrawable = activity!!.resources.getDrawable(R.drawable.custom_progress_bar)


        levelText.text = (MainMenuActivity.user!!.levelup_points / 200 + 1).toString() + " Уровень"
        levelPrev.text = (MainMenuActivity.user!!.levelup_points / 200).toString() + " уровень"
        levelNext.text = (MainMenuActivity.user!!.levelup_points / 200 + 2).toString() + " уровень"

        progressBarLevel.progress = (MainMenuActivity.user!!.levelup_points % 200)
        textPointsGotValue.text = (MainMenuActivity.user!!.levelup_points).toString() + " балла"
        textPointsNeedValue.text = (200 - (MainMenuActivity.user!!.levelup_points % 200)).toString() + " балла"
        textPointsTasksDoneValue.text = MainMenuActivity.user!!.completed_tasks.toString()
        when(MainMenuActivity.user!!.levelup_points){
            in 0..200 -> {
                textPointsLevelValue.text = "Новичок"
            }
            in 200..400 -> {
                textPointsLevelValue.text = "Специалист"
            }
            in 400..600 -> {
                textPointsLevelValue.text = "Мастер"
            }
            in 600..800 -> {
                textPointsLevelValue.text = "Чемпион"
            }
            else -> {
                textPointsLevelValue.text = "Гроссмейстер"
            }
        }

        service.getTasksById(MainMenuActivity.user?.id!!).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    App.showProgress(activity!!)
                }
                .doFinally{
                    App.hideProgress()
                }
                .subscribe(
                        {
                            val list = it as ArrayList<Task>
                            val filter = ArrayList<Task>()
                            for(i in list){
                                if(i.status.toLowerCase() == "onprogress"){
                                    filter.add(i)
                                }
                            }
                            setTasks(filter)
                        },
                        {
                            Logger.msg("accepted", it.message)
                        })



    }

    private fun setTasks(taskList: ArrayList<Task>){
        recyclerCurrentTasks?.layoutManager = LinearLayoutManager(activity)
        currentTasksAdapter = CurrentTasksAdapter(taskList, this)
        recyclerCurrentTasks?.adapter = currentTasksAdapter
    }


    override fun onItemClicked(course: Task) {
        var tmpIntent = Intent(activity, TaskDetailActivity::class.java)
        tmpIntent.putExtra(Constants.TASK, course as Parcelable)
        startActivity(tmpIntent)
    }

}