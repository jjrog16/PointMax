package com.example.android.pointmax.ui.card_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.android.pointmax.R

class CardDetailsFragment : Fragment() {
    
    companion object {
        fun newInstance() = CardDetailsFragment()
    }
    
    private lateinit var viewModel: CardDetailsViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card_details, container, false)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CardDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }
    
}
