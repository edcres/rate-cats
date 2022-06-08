package com.example.ratecats.data.room

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritedImgDao {
    @Query("SELECT * FROM favorite_img_table ORDER BY img_url ASC")
    fun getFavoritedImages(): Flow<List<LocalFavoritedImg>>
}