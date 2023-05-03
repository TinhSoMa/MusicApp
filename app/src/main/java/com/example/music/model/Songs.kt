package com.example.music.model
import android.os.Parcel
import android.os.Parcelable

data class Songs(
    val s_id: String?,
    val s_name: String?,
    val ar_image: String?,
    val ar_name: String?,
    val s_likes: String?,
    val s_mp3: String?,
    val s_image: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(s_id)
        dest.writeString(s_name)
        dest.writeString(ar_image)
        dest.writeString(ar_name)
        dest.writeString(s_likes)
        dest.writeString(s_mp3)
        dest.writeString(s_image)
    }

    companion object CREATOR : Parcelable.Creator<Songs> {
        override fun createFromParcel(parcel: Parcel): Songs {
            return Songs(parcel)
        }

        override fun newArray(size: Int): Array<Songs?> {
            return arrayOfNulls(size)
        }
    }
}

