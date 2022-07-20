package com.example.ratecats.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritedImgDao {
    @Query("SELECT * FROM favorite_img_table ORDER BY img_url ASC")
    fun getFavoritedImages(): Flow<List<LocalFavoritedImg>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(localFavoritedImg: LocalFavoritedImg)

    @Delete
    suspend fun delete(localFavoritedImg: LocalFavoritedImg)
}