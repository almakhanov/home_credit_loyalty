package kz.batana.homecreditloyalty.task

import android.annotation.SuppressLint
import kz.batana.homecreditloyalty.core.util.BasePresenter
import kz.batana.homecreditloyalty.core.util.Logger
import kz.batana.homecreditloyalty.entity.Task

class TaskPresenter(private val repository:TaskContract.TaskRepository):TaskContract.TaskPresenter, BasePresenter<TaskContract.TaskView>(){
    @SuppressLint("CheckResult")
    override fun getTasks(userId: Int) {
        repository.getTasks(userId)
                .doOnSubscribe { getView()?.showProgress() }
                .doOnComplete { getView()?.hideProgress() }
                .subscribe({
                    val list = it as ArrayList<Task>
                    val filter = ArrayList<Task>()
                    for(i in list){
                        if(i.status.toLowerCase() == "onprogress"){
                            filter.add(i)
                        }
                    }
                    getView()?.showTasks(filter)
                },{
                    Logger.msg("Accepted", it.message)
                })
    }

    override fun viewIsReady() {

    }

    override fun destroy() {

    }

}