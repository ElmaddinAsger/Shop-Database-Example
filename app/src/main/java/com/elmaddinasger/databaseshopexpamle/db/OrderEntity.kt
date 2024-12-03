package com.elmaddinasger.databaseshopexpamle.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "OrderTable",
    foreignKeys = [
        ForeignKey(
            entity = CustomerEntity::class,
            parentColumns = ["customerId"],
            childColumns = ["customerId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ForeignKey(
        entity = ProductEntity::class,
        parentColumns = ["productId"],
        childColumns = ["productId"],
        onDelete = ForeignKey.CASCADE
    )
    ])
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val orderId: Long,
    val customerId: Long,
    val productId: Long
)
