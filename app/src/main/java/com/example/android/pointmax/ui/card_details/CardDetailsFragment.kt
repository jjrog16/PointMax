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
        val application = requireNotNull(activity).application
        val binding: FragmentCardDetailsBinding = FragmentCardDetailsBinding.inflate(layoutInflater)
        binding.setLifecycleOwner(this)
        
        // TODO: Fix safe arg binding
        val card = CardDetailsFragmentArgs.fromBundle(requireArguments()).selectedCard
        val viewModelFactory = CardDetailsViewModelFactory(card, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(CardDetailsViewModel::class.java)
        return binding.root
    }
    
}
