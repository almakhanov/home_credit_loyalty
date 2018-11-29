package kz.batana.homecreditloyalty.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "History")
data class History(
        @PrimaryKey
        var title: String,
        var data: String,
        var from_field: String,
        var to_field: String,
        var balance: Int
)