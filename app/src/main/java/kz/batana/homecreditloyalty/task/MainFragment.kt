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
import kz.batana.homecreditloyalty.tasks_tabs.TasksService
import org.koin.android.ext.android.inject


@Suppress("DEPRECATION")
class MainFragment: Fragment(), CurrentTasksAdapter.OnItemClickListener {

    private lateinit var currentTasksAdapter: CurrentTasksAdapter
    private val service: TasksService by inject()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tasks_fragment,container,false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        service.getTasks().subscribeOn(Schedulers.io())
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


        progressBarLevel.visibility = View.VISIBLE
        progressBarLevel.progress = 0
        progressBarLevel.max = 100
        progressBarLevel.progressDrawable = activity!!.resources.getDrawable(R.drawable.custom_progress_bar)
        progressBarLevel.progress = 75
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