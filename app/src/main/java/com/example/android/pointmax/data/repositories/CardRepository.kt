package com.example.android.pointmax.data.repositories

import androidx.lifecycle.LiveData
import com.example.android.pointmax.data.database.CardDao
import com.example.android.pointmax.data.database.entities.Card

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class CardRepository(private val cardDao: CardDao) {
    
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCards: LiveData<List<Card>> = cardDao.getCards()
    
    
    suspend fun insert(card: Card) {
        cardDao.insert(card)
    }
    
    suspend fun deleteByName(name: String){
        cardDao.deleteByName(name)
    }
    
//    fun getAllBestCards(categoryChosen: String): LiveData<List<Card>> {
//        return cardDao.getAllBestCards(categoryChosen)
//    }
    
}