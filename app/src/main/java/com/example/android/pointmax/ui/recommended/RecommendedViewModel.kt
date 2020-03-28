package com.example.android.pointmax.ui.recommended

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecommendedViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "R"
    }
    val text: LiveData<String> = _text
}