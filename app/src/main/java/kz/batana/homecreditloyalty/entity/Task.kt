package kz.batana.homecreditloyalty.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "Task")
data class Task(
        @PrimaryKey
        @SerializedName("id") var id: Int,
        @SerializedName("title") var title: String,
        @SerializedName("date") var date: String,
        @SerializedName("type") var expiredDate: String?,
        @SerializedName("description") var description: String,
        @SerializedName("bonus") var bonus: Int,
        @SerializedName("status") var status: String, //done, onProgress, failed, new
        @SerializedName("donePercent") var donePercent: Int
) : Serializable