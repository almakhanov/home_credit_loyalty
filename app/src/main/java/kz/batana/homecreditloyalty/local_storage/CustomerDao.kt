package kz.batana.homecreditloyalty.local_storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import kz.batana.homecreditloyalty.entity.Customer

@Dao
interface CustomerDao{
    @Insert
    fun insert(consumer: Customer)

    @Query("SELECT * FROM Customer")
    fun get(): Flowable<List<Customer>>

    @Query("DELETE FROM Customer")
    fun delete()
}