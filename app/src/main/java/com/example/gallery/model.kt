package com.example.gallery

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Attachments(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("type") val type: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("duration") var duration: String = "",
) : Serializable