package com.example.android.pointmax.ui.wallet

import android.app.Application
import androidx.lifecycle.*
import com.example.android.pointmax.database.Card
import com.example.android.pointmax.database.CardDao
import com.example.android.pointmax.database.CardRepository
import com.example.android.pointmax.database.CardRoomDatabase

class WalletViewModel(
    val database: CardDao,
    application: Application
) : AndroidViewModel(application) {
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

//    private val _text = MutableLiveData<String>().apply {
//        value = "Wallet"
//    }
//    val text: LiveData<String> = _text
}