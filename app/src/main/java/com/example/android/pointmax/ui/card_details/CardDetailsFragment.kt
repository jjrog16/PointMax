package com.example.android.pointmax.ui.card_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.android.pointmax.databinding.FragmentCardDetailsBinding

class CardDetailsFragment : Fragment() {
    
    companion object {
        fun newInstance() = CardDetailsFragment()
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCardDetailsBinding = FragmentCardDetailsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = ViewModelProvider(
            this).get(CardDetailsViewModel::class.java)
            
        return binding.root
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        
    }
    
}
