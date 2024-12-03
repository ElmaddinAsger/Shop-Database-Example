package com.elmaddinasger.databaseshopexpamle.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ProductEntity::class,CustomerEntity::class, OrderEntity::class],
    version = 1
)
abstract class ProductDatabase() : RoomDatabase() {
    abstract fun productDao() : ProductDao
    abstract fun customerDao() : CustomerDao
    abstract fun orderDao() : OrderDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getDatabase (context: Context):ProductDatabase{
           return INSTANCE ?: synchronized(this){
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   ProductDatabase::class.java,
                   "AppDatabase"
               ).build()
               INSTANCE = instance
               instance
           }
        }
    }
}