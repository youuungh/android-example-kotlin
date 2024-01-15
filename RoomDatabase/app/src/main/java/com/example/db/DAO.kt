package com.example.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.Food

@Dao
interface DAO {
    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFoods(vararg foods: Food)
    @Insert
    fun insertFood(food: Food)
    @Query("INSERT INTO foods(food_name, category, price) VALUES (:name, :category, :price)")
    fun myInsertFood(name: String, category: String, price: Int)

    // Delete
    @Query("DELETE FROM foods where id = :id")
    fun deleteFood(id: Long)
    @Query("DELETE FROM foods")
    fun deleteAll()

    // Update
    @Query("UPDATE foods SET food_name = :name WHERE id = :id")
    fun updateName(id: Long, name: String)
    @Query("UPDATE foods SET category = :category WHERE id = :id")
    fun updateCategory(id: Long, category: String)
    @Query("UPDATE foods SET price = :price WHERE id = :id")
    fun updatePrice(id: Long, price: Int)

    //Query
    @Query("SELECT * FROM foods")
    fun getAll(): List<Food>
    @Query("SELECT food_name FROM foods")
    fun getNameAll(): List<String>
    @Query("SELECT * FROM foods where id = :id")
    fun getFood(id: Long): Food
    @Query("SELECT COUNT(*) FROM foods")
    fun getCount(): Int
    @Query("SELECT EXISTS(SELECT * FROM foods where id = :id)")
    fun isFood(id: Long): Int
}