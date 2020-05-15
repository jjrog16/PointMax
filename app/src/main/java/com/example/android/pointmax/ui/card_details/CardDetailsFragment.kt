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
        binding.setLifecycleOwner(this)
        
        // TODO: Fix safe arg binding
        val card = CardDetailsFragmentArgs.fromBundle(requireArguments()).selectedCard
        val viewModelFactory = CardDetailsViewModelFactory(card, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(CardDetailsViewModel::class.java)
    
        setHasOptionsMenu(true)
        return binding.root
    }
    
    /**
     * Inflates the overflow menu that contains options.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
