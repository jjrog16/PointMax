package com.example.android.pointmax.ui.card_details

import android.app.Application
import android.view.View
import android.widget.TextView
import androidx.lifecycle.*
import com.example.android.pointmax.database.Card
import com.example.android.pointmax.database.CardRepository
import com.example.android.pointmax.database.CardRoomDatabase

class CardDetailsViewModel(card: Card, application: Application) : AndroidViewModel(application) {
    private val _selectedCard = MutableLiveData<Card>()
    
    // The external LiveData for the SelectedCard
    val selectedCard: LiveData<Card>
        get() = _selectedCard
    
    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedCard.value = card
    }

    private var _text = MutableLiveData<String>().apply {
        value = _selectedCard.value.toString()
    }
    val text: LiveData<String> = _text
}
