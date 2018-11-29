package kz.batana.homecreditloyalty.task

import io.reactivex.Observable
import kz.batana.homecreditloyalty.core.util.IPresenter
import kz.batana.homecreditloyalty.core.util.IView
import kz.batana.homecreditloyalty.entity.Task

interface TaskContract{
    interface TaskView: IView<TaskPresenter>{
        fun showTasks(tasks: ArrayList<Task>)
        fun showProgress()
        fun hideProgress()
    }

    interface TaskPresenter: IPresenter<TaskView>{
        fun getTasks(userId:Int)
    }

    interface TaskRepository{
        fun getTasks(userId:Int): Observable<List<Task>>
    }

}