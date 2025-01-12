package com.elthobhy.core.data.source.local.entity.headline


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elthobhy.core.data.source.local.entity.Source
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "article_entities")
data class ArticleHeadlineEntity(

    @ColumnInfo(name = "publishedAt")
    val publishedAt: String? = null,

    @ColumnInfo(name = "author")
    val author: String? =null,


    @ColumnInfo(name = "content")
    val content: String? = null,

    @ColumnInfo(name = "description")
    val description: String? =null,

    @Embedded
    var source: Source? =null,

    @PrimaryKey
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "url")
    val url: String? =null,

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String? =null,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false,

    @ColumnInfo(name = "detik")
    var detik: Boolean = false,

    @ColumnInfo(name = "suara")
    var suara: Boolean = false,

    @ColumnInfo(name = "kapanlagi")
    var kapanlagi: Boolean = false,

    @ColumnInfo(name = "liputan")
    var liputan: Boolean = false,
) : Parcelable