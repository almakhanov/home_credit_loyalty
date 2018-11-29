package kz.batana.homecreditloyalty.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Customer (
        @SerializedName("id") var id: Int,
        @SerializedName("name") var name : String,
        @SerializedName("password") var password: String,
        @SerializedName("email") var email: String,
        @SerializedName("phone") var phone: String,
        @SerializedName("age") var age: Int,
        @SerializedName("current_points") var current_points: Int,
        @SerializedName("completed_tasks") var completed_tasks: Int,
        @SerializedName("rank") var rank: String,
        @SerializedName("levelup_points") var levelup_points: Int,
        @SerializedName("tasks")val tasks: List<Int>
) : Serializable