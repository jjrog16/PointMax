package com.example.android.pointmax.ui.AddCardToWallet

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.pointmax.R
import com.example.android.pointmax.database.Card
import com.example.android.pointmax.database.CardRepository
import com.example.android.pointmax.database.CardRoomDatabase
import kotlinx.android.synthetic.main.fragment_add_card_to_wallet.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCardToWalletViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CardRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
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
}
