package com.example.android.pointmax.ui.wallet

import android.app.Application
import androidx.lifecycle.*
import com.example.android.pointmax.database.Card
import com.example.android.pointmax.database.CardRepository
import com.example.android.pointmax.database.CardRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WalletViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CardRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    
    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _allCards = MutableLiveData<List<Card>>()
    
    // The external LiveData interface to the property is immutable, so only this class can modify
    val allCards: LiveData<List<Card>>
        get() = _allCards
    
    // Internally, we use a MutableLiveData to handle navigation to the selected property
    private val _navigateToSelectedCard = MutableLiveData<Card>()
    
    // The external immutable LiveData for the navigation property
    val navigateToSelectedCard: LiveData<Card>
        get() = _navigateToSelectedCard
    
    // TODO: Complete the transfer of mars property info to Wallet info
    
    init {
        val cardsDao = CardRoomDatabase.getDatabase(application, viewModelScope).cardDao()
        repository = CardRepository(cardsDao)
        _allCards = repository.allCards
    }
    
    
    
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(card: Card) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(card)
    }
}