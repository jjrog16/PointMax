package com.example.android.pointmax.ui.card_details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.pointmax.database.Card

/**
 * Simple ViewModel factory that provides the MarsProperty and context to the ViewModel.
 */
class CardDetailsViewModelFactory(
    private val card: Card,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardDetailsViewModel::class.java)) {
            return CardDetailsViewModel(card, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}