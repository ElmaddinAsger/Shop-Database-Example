package com.elmaddinasger.databaseshopexpamle.db

import androidx.room.Dao
import androidx.room.Index
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {
    @Query("SELECT * FROM OrderTable")
    fun getAll(): List<OrderEntity>
    @Insert
    fun insert(order: OrderEntity): Long
}