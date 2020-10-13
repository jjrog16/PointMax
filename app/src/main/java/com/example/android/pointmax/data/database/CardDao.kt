package com.example.android.pointmax.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.pointmax.data.database.entities.Card


@Dao
interface CardDao {
    
    @Query("SELECT * from Card")
    fun getCards(): LiveData<List<Card>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: Card)

    @Query("DELETE FROM Card")
    suspend fun deleteAll()
    
    @Query("DELETE FROM Card WHERE cardName = :name")
    suspend fun deleteByName(name: String)
}