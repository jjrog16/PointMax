package com.example.android.pointmax.ui.wallet

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.pointmax.database.Card
import com.example.android.pointmax.database.CardRepository
import com.example.android.pointmax.database.CardRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WalletViewModel/*(application: Application)*/ : ViewModel() {
//    private val repository: CardRepository
//    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
//    // - We can put an observer on the data (instead of polling for changes) and only update the
//    //   the UI when the data actually changes.
//    // - Repository is completely separated from the UI through the ViewModel.
//    val allCards: LiveData<List<Card>>
//
//    init {
//        val cardsDao = CardRoomDatabase.getDatabase(application, viewModelScope).cardDao()
//        repository = CardRepository(cardsDao)
//        allCards = repository.allCards
//    }
//
//    /**
//     * Launching a new coroutine to insert the data in a non-blocking way
//     */
//    fun insert(card: Card) = viewModelScope.launch(Dispatchers.IO) {
//        repository.insert(card)
//    }

    private val _text = MutableLiveData<String>().apply {
        value = "Wallet"
    }
    val text: LiveData<String> = _text
}