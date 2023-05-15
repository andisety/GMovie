package com.andi.gmovie.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movie")
class MovieEntity(

    @field:ColumnInfo(name = "id")
    @field:PrimaryKey
    val id: Int,

    @field:ColumnInfo(name = "title")
    val title: String,

    @field:ColumnInfo(name = "url")
    val url: String,

    @field:ColumnInfo(name = "desc")
    val desc: String ,

    @field:ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean ,

) : Parcelable