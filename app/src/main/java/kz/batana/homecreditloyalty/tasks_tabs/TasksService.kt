package kz.batana.homecreditloyalty.tasks_tabs

import io.reactivex.Observable
import kz.batana.homecreditloyalty.entity.Task
import retrofit2.http.GET

interface TasksService {

    @GET("tasks")
    fun getTasks() : Observable<List<Task>>

}