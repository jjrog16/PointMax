package com.example.android.pointmax.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CardDao {
    @Transaction
    @Query("SELECT * from Card")
    fun getCards(): LiveData<List<Card>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: Card)

    @Query("DELETE FROM Card")
    suspend fun deleteAll()
    
    @Query("DELETE FROM Card WHERE cardName = :name")
    suspend fun deleteByName(name: String)
    
    @Query("UPDATE Card SET cardName = :newName WHERE cardName = :oldName")
    suspend fun editName(newName: String, oldName: String)
}