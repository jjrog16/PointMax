package com.example.android.pointmax.ui.wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WalletViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "W"
    }
    val text: LiveData<String> = _text
}