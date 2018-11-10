package kz.batana.homecreditloyalty.tasks_tabs


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tasks_base.*

import kz.batana.homecreditloyalty.R

class TasksBaseFragment : Fragment() {

    private lateinit var todoTasksFragment: TodoTasksFragment
    private lateinit var doingTasksFragment: DoingTasksFragment
    private lateinit var doneTasksFragment: DoneTasksFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //View pager fragments
        val adapter = TasksFragmentPagerAdapter(childFragmentManager)
        todoTasksFragment = TodoTasksFragment()
        doingTasksFragment = DoingTasksFragment()
        doneTasksFragment = DoneTasksFragment()

        adapter.addFragment(todoTasksFragment, "Новые")
        adapter.addFragment(doingTasksFragment, "В прогресе")
        adapter.addFragment(doneTasksFragment, "Сделано")
        view_pager_home.adapter = adapter
        tab_layout_home.setupWithViewPager(view_pager_home)
    }



}
