package com.elthobhy.newsapp.data.source.local.entity


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Source(
    val id: String? = null,
    val name: String?=null,
) : Parcelable