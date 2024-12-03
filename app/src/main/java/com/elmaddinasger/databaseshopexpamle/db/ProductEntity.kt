package com.elmaddinasger.databaseshopexpamle.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductTable")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val productId: Long,
    val productName: String,
    val productQuality: Long,
){
    override fun toString(): String {
        return productName
    }
}
