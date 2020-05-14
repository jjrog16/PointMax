package com.example.android.pointmax.ui.card_details

import android.app.Application
import androidx.lifecycle.*

class CardDetailsViewModel(card: String, application: Application) : AndroidViewModel(application) {
    
    // Because we need only the name of the card passed as a variable, it is of type String
    private val _selectedCard = MutableLiveData<String>()
    
    // The external LiveData for the SelectedCard
    val selectedCard: String
        get() = _selectedCard.toString()
    
    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedCard.value = card
    }

    private var _text = MutableLiveData<String>().apply {
        value = _selectedCard.value.toString()
    }
    val text: LiveData<String> = _text
}
