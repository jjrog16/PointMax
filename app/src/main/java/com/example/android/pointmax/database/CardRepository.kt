package com.example.android.pointmax.database

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class CardRepository(private val cardDao: CardDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCards: LiveData<List<Card>> = cardDao.getCards()

    suspend fun insert(card: Card) {
        cardDao.insert(card)
    }
    
    suspend fun deleteByName(card: String){
        cardDao.deleteByName(card)
    }
    
    suspend fun editName(newName: String, oldName: String){
        cardDao.editName(newName, oldName)
    }
}