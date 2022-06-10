package com.example.ratecats.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LocalFavoritedImg::class],
    version = 2,
    exportSchema = false
)
abstract class CatsRoomDatabase: RoomDatabase() {

    abstract fun favoritedImgDao(): FavoritedImgDao

    companion object {
        @Volatile
        private var INSTANCE: CatsRoomDatabase? = null
        private const val DATABASE_NAME = "cats_database"

        fun getInstance(context: Context): CatsRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    CatsRoomDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE= instance
                instance
            }
        }
    }
}