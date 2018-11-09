package kz.batana.homecreditloyalty.entity

import com.google.gson.annotations.SerializedName

data class Task(
        @SerializedName("id") var id: Int,
        @SerializedName("title") var title: String,
        @SerializedName("date") var date: String,
        @SerializedName("expiredDate") var expiredDate: String?,
        @SerializedName("description") var description: String,
        @SerializedName("bonus") var bonus: Int,
        @SerializedName("status") var status: String, //done, onProgress, failed, new
        @SerializedName("donePercent") var donePercent: Int
)