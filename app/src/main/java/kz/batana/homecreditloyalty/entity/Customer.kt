package kz.batana.homecreditloyalty.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Customer (
        @SerializedName("name") var name : String,
        @SerializedName("password") var password: String,
        @SerializedName("email") var email: String,
        @SerializedName("phone") var phone: String,
        @SerializedName("age") var age: Int,
        @SerializedName("current_points") var current_points: Int,
        @SerializedName("completed_tasks") var completed_tasks: Int,
        @SerializedName("rank") var rank: String
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(password)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeInt(age)
        parcel.writeInt(current_points)
        parcel.writeInt(completed_tasks)
        parcel.writeString(rank)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Customer> {
        override fun createFromParcel(parcel: Parcel): Customer {
            return Customer(parcel)
        }

        override fun newArray(size: Int): Array<Customer?> {
            return arrayOfNulls(size)
        }
    }
}