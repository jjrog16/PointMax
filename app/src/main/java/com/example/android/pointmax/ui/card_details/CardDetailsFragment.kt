package com.example.android.pointmax.ui.card_details

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.android.pointmax.databinding.FragmentCardDetailsBinding
import kotlinx.android.synthetic.main.fragment_card_details.*
import timber.log.Timber

class CardDetailsFragment : Fragment() {
    
    companion object {
        fun newInstance() = CardDetailsFragment()
    }
    
    private lateinit var viewModel: CardDetailsViewModel
    private lateinit var currentCardText: TextView
    
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
    
        viewModel = ViewModelProvider(
            this, viewModelFactory).get(CardDetailsViewModel::class.java)
        
        // Giving the binding access to the CardDetailsViewModel
        binding.viewModel = viewModel
        
        return binding.root
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
        currentCardText = current_card
        
        delete_button.setOnClickListener {
            val currentCard = currentCardText.text.toString().toUpperCase()
            currentCard.let {
                viewModel.deleteByName(it)
            }
        
            // Once value is deleted into the database, go back to the wallet
            val action =
                CardDetailsFragmentDirections.actionCardDetailsFragmentToNavigationWallet()
            findNavController().navigate(action)
        }
        
        edit_button.setOnClickListener {
            val currentCard = currentCardText.text.toString()
            
            // Once edit is pressed, go to AddCustomCard and pass in the value of the current card
            val action =
                CardDetailsFragmentDirections.actionCardDetailsFragmentToAddCustomCardFragment(currentCard)
            findNavController().navigate(action)
            
        }
    }
}
