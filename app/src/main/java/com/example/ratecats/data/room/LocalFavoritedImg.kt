package com.example.ratecats.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_img_table")
data class LocalFavoritedImg (
    @PrimaryKey
    @ColumnInfo(name = "img_id")
    val imgId: String,
    @ColumnInfo(name = "id")
    var id: String?,
    @ColumnInfo(name = "sub_id")
    val subId: String?,
    @ColumnInfo(name = "img_url")
    val imgUrl: String
)