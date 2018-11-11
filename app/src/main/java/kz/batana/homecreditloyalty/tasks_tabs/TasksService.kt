package kz.batana.homecreditloyalty.tasks_tabs

import io.reactivex.Observable
import kz.batana.homecreditloyalty.entity.Customer
import kz.batana.homecreditloyalty.entity.Task
import retrofit2.http.GET
import retrofit2.http.Path

interface TasksService {

    @GET("get_tasks/")
    fun getTasks() : Observable<List<Task>>

    @GET("add_point/{user_id}/{task_id}")
    fun addPoint(@Path("user_id") id: Int,
                 @Path("task_id") task_id: Int): Observable<Customer>

}