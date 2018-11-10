package kz.batana.homecreditloyalty.tasks_tabs


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
import kotlinx.android.synthetic.main.fragment_doing_tasks.*
import kotlinx.android.synthetic.main.fragment_todo_tasks.*
import kz.batana.homecreditloyalty.App
import kz.batana.homecreditloyalty.Constants

import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.core.util.Logger
import kz.batana.homecreditloyalty.entity.Task
import kz.batana.homecreditloyalty.task.CurrentTasksAdapter
import kz.batana.homecreditloyalty.task.TaskDetailActivity
import org.koin.android.ext.android.inject


class DoingTasksFragment : Fragment(), CurrentTasksAdapter.OnItemClickListener {

    private val service: TasksService by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doing_tasks, container, false)
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
                            setRecycler(filter)
                        },
                        {
                            Logger.msg("accepted", it.message)
                        })
    }

    private fun setRecycler(list: ArrayList<Task>){
        recycler_doing.layoutManager = LinearLayoutManager(context)
        recycler_doing.adapter = CurrentTasksAdapter(list, this)
    }

    override fun onItemClicked(course: Task) {
        var tmpIntent = Intent(activity, TaskDetailActivity::class.java)
        tmpIntent.putExtra(Constants.TASK, course as Parcelable)
        startActivity(tmpIntent)
    }

}
