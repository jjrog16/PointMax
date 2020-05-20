package com.example.android.pointmax.ui.card_details

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.pointmax.R

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
    
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        
        // The card name string value passed through the fragment
        val card = CardDetailsFragmentArgs.fromBundle(requireArguments()).selectedCard
        
        // The ViewModelFactory that takes the card name string and creates a ViewModel
        // with the card name string
        val viewModelFactory = CardDetailsViewModelFactory(card, application)
    
        // Giving the binding access to the CardDetailsViewModel
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(CardDetailsViewModel::class.java)
    
        
        
        
        
        return binding.root
    }
}
