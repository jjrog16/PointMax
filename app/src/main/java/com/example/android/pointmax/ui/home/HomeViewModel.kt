package com.example.android.pointmax.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.example.android.pointmax.database.Card
import com.example.android.pointmax.database.CardRepository
import com.example.android.pointmax.database.CardRoomDatabase

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: CardRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCards: LiveData<List<Card>>
    
//    var mutableBestCard: MutableLiveData<String> = MutableLiveData()
    
    
    init {
        val cardsDao = CardRoomDatabase.getDatabase(application, viewModelScope).cardDao()
        repository = CardRepository(cardsDao)
        allCards = repository.allCards
    }
    
//    fun getBestCardByCategory(category: String) : LiveData<List<Card>> {
//        return repository.getAllBestCards(category)
//    }
    
    
//    val categoryObservable : LiveData<List<Card>> = Transformations.switchMap(mutableBestCard) { param ->
//        repository.getAllBestCards(param)
//    }


//    fun orderByCategory(param: String){
//        mutableBestCard.value = param
//    }
}