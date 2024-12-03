package com.elmaddinasger.databaseshopexpamle.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customertable")
    fun getAll(): List<CustomerEntity>
    @Insert
    fun insert(customer: CustomerEntity): Long
}