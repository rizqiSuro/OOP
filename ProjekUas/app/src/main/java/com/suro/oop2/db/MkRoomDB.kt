package com.suro.oop2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.suro.oop2.model.Mk

//Database annotation to specify the entities and set version
@Database(entities = [Mk::class], version = 1, exportSchema = false)
abstract class MkRoomDB : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: MkRoomDB? = null

        fun getDatabase(context: Context): MkRoomDB {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MkRoomDB::class.java,
                    "mk_db"
                )
                    .allowMainThreadQueries() //allows Room to executing task in main thread
                    .fallbackToDestructiveMigration() //allows Room to recreate database if no migrations found
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getMkDao(): MkDao
}