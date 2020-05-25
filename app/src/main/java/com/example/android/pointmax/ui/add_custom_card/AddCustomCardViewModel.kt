package com.example.android.pointmax.ui.add_custom_card

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.pointmax.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddCustomCardViewModel(cardName: String? = null, application: Application) : AndroidViewModel(application) {
    private val repository: CardRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    
    // The external LiveData interface to the property is immutable, so only this class can modify
    val allCards: LiveData<List<CreditCards>>
    
    // Because we need only the name of the card passed as a variable, it is of type String
    private val _cardToEdit = MutableLiveData<String>()
    
    // The external LiveData for the cardToEdit
    val cardToEdit: String
        get() = _cardToEdit.toString()
    
    init {
        if (cardName != null) {
            _cardToEdit.value = cardName
        }
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
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertCategory(category: Category) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertCategory(category)
    }
    
    /**
     * Launching a new coroutine to update the data in a non-blocking way
     */
    fun edit(newName: String, oldName: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.editName(newName, oldName)
    }
}