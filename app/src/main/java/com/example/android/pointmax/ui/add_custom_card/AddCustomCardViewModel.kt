package com.example.android.pointmax.ui.add_custom_card

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.pointmax.data.database.*
import com.example.android.pointmax.data.database.entities.Card
import com.example.android.pointmax.data.repositories.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddCustomCardViewModel(cardName: String?, application: Application) : AndroidViewModel(application) {
    private val repository: CardRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCards: LiveData<List<Card>>
    
    init {
        val cardsDao = CardRoomDatabase.getDatabase(application, viewModelScope).cardDao()
        repository = CardRepository(cardsDao)
        allCards = repository.allCards
    }
    
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(card: Card) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(card)
    }
    
    /**
     * Launching a new coroutine to delete the data in a non-blocking way
     */
    fun deleteByName(cardName: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteByName(cardName)
    }
    
}