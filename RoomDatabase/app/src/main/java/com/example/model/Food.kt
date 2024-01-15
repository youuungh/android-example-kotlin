package com.example.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class Food(
    @ColumnInfo(name = "food_name")
    var name: String?,
    var category: String?,
    var price: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override fun toString(): String {
        return "id = $id, name = $name, category = $category, price = $price"
    }
}
