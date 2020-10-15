package com.example.android.pointmax.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.example.android.pointmax.data.database.entities.Card
import com.example.android.pointmax.data.repositories.CardRepository
import com.example.android.pointmax.data.database.CardRoomDatabase

class HomeViewModel(private val repository: CardRepository,application: Application) : AndroidViewModel(application) {
    
    
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCards: LiveData<List<Card>> = repository.allCards
    
//    init {
//        val cardsDao = CardRoomDatabase.getDatabase(application, viewModelScope).cardDao()
//        repository = CardRepository(cardsDao)
//        allCards = repository.allCards
//    }
    
}