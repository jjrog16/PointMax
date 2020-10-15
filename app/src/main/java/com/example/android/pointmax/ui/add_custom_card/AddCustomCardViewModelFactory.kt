package com.example.android.pointmax.ui.add_custom_card

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Simple ViewModel factory that provides the card name and context to the ViewModel.
 */
//class AddCustomCardViewModelFactory(
//    private val cardName: String?,
//    private val application: Application
//) : ViewModelProvider.Factory {
//    @Suppress("unchecked_cast")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(AddCustomCardViewModel::class.java)) {
//            return AddCustomCardViewModel(cardName, application) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}