package com.example.android.pointmax.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CardDao {

    @Query("SELECT * from card_table")
    fun getCards(): LiveData<List<Card>>

    @Insert
    suspend fun insert(card: Card)

    @Query("DELETE FROM card_table")
    suspend fun deleteAll()
}