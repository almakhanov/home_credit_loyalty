package kz.batana.homecreditloyalty.local_storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import kz.batana.homecreditloyalty.entity.Task

@Dao
interface TaskDao{
    @Insert
    fun insert(item: Task)

    @Query("SELECT * FROM Task")
    fun get(): Flowable<List<Task>>

    @Query("DELETE FROM Task")
    fun delete()
}