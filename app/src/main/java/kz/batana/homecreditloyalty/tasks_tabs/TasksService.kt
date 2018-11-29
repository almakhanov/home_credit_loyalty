package kz.batana.homecreditloyalty.tasks_tabs

import io.reactivex.Observable
import kz.batana.homecreditloyalty.entity.Customer
import kz.batana.homecreditloyalty.entity.Task
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TasksService {

    @GET("get_tasks/")
    fun getTasks() : Observable<List<Task>>

    @GET("add_point/{user_id}/{task_id}")
    fun addPoint(@Path("user_id") id: Int,
                 @Path("task_id") task_id: Int): Observable<Customer>
    @GET("{user_id}/get_tasks")
    fun getTasksById(@Path("user_id") id:Int) :Observable<List<Task>>

    @GET("pay/{user_id}/{balance}")
    fun pay(@Path("user_id") id: Int,
                 @Path("balance") balance: Int): Observable<ResponseBody>


    @GET("add_task/")
    fun addTask(@Query("title") title: String,
                @Query("body") body:String) :Observable<Int>
}