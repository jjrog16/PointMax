package com.example.android.pointmax.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CardDao {

    @Query("SELECT * from card_table")
    fun getCards(): LiveData<List<Card>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: Card)

    @Query("DELETE FROM card_table")
    suspend fun deleteAll()
    
    @Query("DELETE FROM card_table WHERE cardName = :name")
    suspend fun deleteByName(name: String)
    
    @Query("UPDATE card_table SET cardName = :newName WHERE cardName = :oldName")
    suspend fun editName(newName: String, oldName: String)
}