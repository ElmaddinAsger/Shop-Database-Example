package com.elmaddinasger.databaseshopexpamle.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CustomerTable")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    val customerId: Long,
    val customerName: String,
    val customerBirthDay: String,
){
    override fun toString(): String {
        return customerName
    }
}
