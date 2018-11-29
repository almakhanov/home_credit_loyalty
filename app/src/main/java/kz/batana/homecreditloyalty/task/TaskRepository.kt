package kz.batana.homecreditloyalty.task

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.homecreditloyalty.entity.Task
import kz.batana.homecreditloyalty.tasks_tabs.TasksService

class TaskRepository(private val remoteService:TasksService):TaskContract.TaskRepository{
    override fun getTasks(userId:Int):Observable<List<Task>> {
        return remoteService.getTasksById(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}