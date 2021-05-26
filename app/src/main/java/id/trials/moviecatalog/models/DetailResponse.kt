package id.trials.moviecatalog.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailResponse(
    @SerializedName("original_title")
    val title: String,
    @SerializedName("poster_path")
    val poster: String?,
    @SerializedName("release_date")
    val release: String,
    @SerializedName("overview")
    val overview: String?
) : Parcelable {
    constructor() : this("", null, "", "")
}