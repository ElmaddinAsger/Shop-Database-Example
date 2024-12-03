package com.elmaddinasger.databaseshopexpamle.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM PRODUCTTABLE")
    fun getAll():List<ProductEntity>
    @Insert
    fun insert(product: ProductEntity):Long
}