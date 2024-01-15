package com.example.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.model.Food

@Database(
    entities = arrayOf(Food::class),
    version = 1,
)

abstract class FoodDatabase: RoomDatabase() {
    abstract fun DAO(): DAO

    companion object {
        private var instance: FoodDatabase? = null

        fun getInstance(context: Context): FoodDatabase? {
            if (instance == null) {
                synchronized(FoodDatabase::class) {
                    instance = Room.databaseBuilder(
                        context,
                        FoodDatabase::class.java,
                        "Menu")
                        .build()
                }
            }
            return instance
        }

        fun deleteInstance() {
            instance = null
        }
    }
}