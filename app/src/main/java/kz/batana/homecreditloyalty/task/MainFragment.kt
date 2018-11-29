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
import kotlinx.android.synthetic.main.tasks_fragment.*
import kz.batana.homecreditloyalty.App
import kz.batana.homecreditloyalty.Constants
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.entity.Task
import kz.batana.homecreditloyalty.mainMenu.MainMenuActivity
import org.koin.android.ext.android.inject


@Suppress("DEPRECATION")
class MainFragment() : Fragment(), CurrentTasksAdapter.OnItemClickListener, TaskContract.TaskView {
    override fun showTasks(tasks: ArrayList<Task>) {
        setTasks(tasks)
    }

    override fun showProgress() {
        App.showProgress(activity!!)
    }

    override fun hideProgress() {
        App.hideProgress()
    }

    override val presenter: TaskContract.TaskPresenter by inject()
    private lateinit var currentTasksAdapter: CurrentTasksAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tasks_fragment,container,false)
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
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


        if (App.internetConnected)
            presenter.getTasks(MainMenuActivity.user!!.id)
        else
            presenter.getTasksLocallY(MainMenuActivity.user!!.id)

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