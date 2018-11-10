package kz.batana.homecreditloyalty.entity

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
data class Task(
        @SerializedName("id") var id: Int,
        @SerializedName("title") var title: String,
        @SerializedName("date") var date: String,
        @SerializedName("type") var expiredDate: String?,
        @SerializedName("description") var description: String,
        @SerializedName("bonus") var bonus: Int,
        @SerializedName("status") var status: String, //done, onProgress, failed, new
        @SerializedName("donePercent") var donePercent: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(date)
        parcel.writeString(expiredDate)
        parcel.writeString(description)
        parcel.writeInt(bonus)
        parcel.writeString(status)
        parcel.writeInt(donePercent)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }
}