package com.infinitumcode.hackernews.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.infinitumcode.hackernews.data.database.converter.DateTypeConverter
import com.infinitumcode.hackernews.data.model.local.HitEntity
import com.infinitumcode.hackernews.utils.DATABASE_NAME

@Database(
    entities = [HitEntity::class], version = 1, exportSchema = false
)
@TypeConverters(DateTypeConverter::class)
abstract class HackerNewsDatabase : RoomDatabase() {
    abstract fun hackerNewsDao(): HackerNewsDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: HackerNewsDatabase? = null

        fun getInstance(context: Context): HackerNewsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HackerNewsDatabase::class.java, DATABASE_NAME
            ).build()
    }
}
