package com.example.android.pointmax.ui.card_details

import android.app.Application
import androidx.lifecycle.*
import com.example.android.pointmax.database.Card
import com.example.android.pointmax.database.CardRepository
import com.example.android.pointmax.database.CardRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardDetailsViewModel(cardName: String, application: Application) : AndroidViewModel(application) {
    private val repository: CardRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    
    // The external LiveData interface to the property is immutable, so only this class can modify
    val allCards: LiveData<List<Card>>
    
    // Because we need only the name of the card passed as a variable, it is of type String
    private val _selectedCard = MutableLiveData<String>()
    
    // The external LiveData for the SelectedCard
    val selectedCard: String
        get() = _selectedCard.toString()
    
    
    
    init {
        // Initialize the _selectedProperty MutableLiveData
        _selectedCard.value = cardName
        
        val cardsDao = CardRoomDatabase.getDatabase(application, viewModelScope).cardDao()
        repository = CardRepository(cardsDao)
        allCards = repository.allCards
    }

    private var _text = MutableLiveData<String>().apply {
        value = _selectedCard.value.toString()
    }
    val text: LiveData<String> = _text
    
    /**
     * Launching a new coroutine to delete the data in a non-blocking way
     */
    fun deleteByName(cardName: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteByName(cardName)
    }
}
