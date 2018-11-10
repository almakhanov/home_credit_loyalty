package kz.batana.homecreditloyalty.task

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.tasks_fragment.*
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.entity.Task


@Suppress("DEPRECATION")
class TasksFragment: Fragment(), CurrentTasksAdapter.OnItemClickListener {

    private lateinit var currentTasksAdapter: CurrentTasksAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tasks_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskList = ArrayList<Task>()
        taskList.apply {
            add(Task(1, "Lorem ipsum dolor sit amet", "20-12-2018", "15-04-2019",
                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                    50, "onProgress", 30))
            add(Task(1, "Lorem ipsum dolor sit amet", "20-12-2018", "15-04-2019",
                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                    50, "onProgress", 30))
            add(Task(1, "Lorem ipsum dolor sit amet", "20-12-2018", "15-04-2019",
                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                    50, "onProgress", 30))
            add(Task(1, "Lorem ipsum dolor sit amet", "20-12-2018", "15-04-2019",
                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                    50, "onProgress", 30))
            add(Task(1, "Lorem ipsum dolor sit amet", "20-12-2018", "15-04-2019",
                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                    50, "onProgress", 30))
            add(Task(1, "Lorem ipsum dolor sit amet", "20-12-2018", "15-04-2019",
                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                    50, "onProgress", 30))
            add(Task(1, "Lorem ipsum dolor sit amet", "20-12-2018", "15-04-2019",
                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                    50, "onProgress", 30))
            add(Task(1, "Lorem ipsum dolor sit amet", "20-12-2018", "15-04-2019",
                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                    50, "onProgress", 30))
        }
        setTasks(taskList)

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
        
    }

}