package kz.batana.homecreditloyalty.local_storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import kz.batana.homecreditloyalty.entity.Customer
import kz.batana.homecreditloyalty.entity.History
import kz.batana.homecreditloyalty.entity.Task

@Database(entities = [
    Customer::class,
    Task::class,
    History::class
], version = 1)
@TypeConverters(Converters::class)
abstract class AppLocalDatabase: RoomDatabase() {
    abstract fun customerDao() : CustomerDao
    abstract fun taskDao() : TaskDao
    abstract fun historyDao() : HistoryDao
}