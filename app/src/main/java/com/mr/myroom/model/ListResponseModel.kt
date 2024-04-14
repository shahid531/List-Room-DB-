package com.mr.myroom.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ListResponseModel(

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("limit")
    val limit: Int? = null,

    @field:SerializedName("skip")
    val skip: Int? = null,

    @field:SerializedName("users")
    val users: List<UsersItem?>? = null
)

@Entity(tableName = "list_table")
data class UsersItem(

    @field:SerializedName("lastName")
    val lastName: String? = null,


    @PrimaryKey
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("email")
    val email: String? = null,


    @field:SerializedName("image")
    val image: String? = null,


    @field:SerializedName("firstName")
    val firstName: String? = null,


    @field:SerializedName("phone")
    val phone: String? = null,


    @field:SerializedName("username")
    val username: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(lastName)
        parcel.writeValue(id)
        parcel.writeString(email)
        parcel.writeString(image)
        parcel.writeString(firstName)
        parcel.writeString(phone)
        parcel.writeString(username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UsersItem> {
        override fun createFromParcel(parcel: Parcel): UsersItem {
            return UsersItem(parcel)
        }

        override fun newArray(size: Int): Array<UsersItem?> {
            return arrayOfNulls(size)
        }
    }
}