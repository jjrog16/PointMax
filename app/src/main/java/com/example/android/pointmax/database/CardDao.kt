package com.example.android.pointmax.database

import androidx.lifecycle.LiveData
import androidx.room.*


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