package com.example.android.pointmax.ui.wallet

import android.app.Application
import androidx.lifecycle.*
import com.example.android.pointmax.database.Card
import com.example.android.pointmax.database.CardRepository
import com.example.android.pointmax.database.CardRoomDatabase
import com.example.android.pointmax.database.CreditCards
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class WalletViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CardRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    
    // The external LiveData interface to the property is immutable, so only this class can modify
    val allCards: LiveData<List<CreditCards>>
    
    // Internally, we use a MutableLiveData to handle navigation to the selected cxa
    private val _navigateToSelectedCard = MutableLiveData<Card>()
    
    // The external immutable LiveData for the navigation property
    val navigateToSelectedCard: LiveData<Card>
        get() = _navigateToSelectedCard
    
    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()
    
    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    
    /**
     * When the card is clicked, set the [_navigateToSelectedCard] [MutableLiveData]
     * @param card The [Card] that was clicked on.
     */
    fun displayCardDetails(card: Card) {
        _navigateToSelectedCard.value = card
    }
    
    /**
     * After the navigation has taken place, make sure navigateToSelectedProperty is set to null
     */
    fun displayCardDetailsComplete() {
        _navigateToSelectedCard.value = null
    }
    
    init {
        val cardsDao = CardRoomDatabase.getDatabase(application, viewModelScope).cardDao()
        repository = CardRepository(cardsDao)
        allCards = repository.allCards
    }
}