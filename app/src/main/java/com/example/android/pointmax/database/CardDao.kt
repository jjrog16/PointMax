package com.example.android.pointmax.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CardDao {
    @Transaction
    @Query("SELECT * from Card")
    fun getCreditCards(): LiveData<List<CreditCards>>
    
    @Query("SELECT * from Card")
    fun getCards(): LiveData<List<Card>>
    
    @Query("SELECT type FROM Category WHERE cardCategoryId = :selectedCard")
    fun getCategories(selectedCard: String): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: Card)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Query("DELETE FROM Card")
    suspend fun deleteAll()
    
    @Query("DELETE FROM Card WHERE cardName = :name")
    suspend fun deleteByName(name: String)
    
    @Query("UPDATE Card SET cardName = :newName WHERE cardName = :oldName")
    suspend fun editName(newName: String, oldName: String)
    
    @Query("UPDATE Category SET cardCategoryId = :newCardCategoryId WHERE cardCategoryId = :currentCardCategoryId" )
    suspend fun editCategoryId(newCardCategoryId: String, currentCardCategoryId: String)
}