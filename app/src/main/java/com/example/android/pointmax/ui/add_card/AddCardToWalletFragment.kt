package com.example.android.pointmax.ui.add_card

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.pointmax.R
import com.example.android.pointmax.database.Card
import kotlinx.android.synthetic.main.fragment_add_card_to_wallet.*
import timber.log.Timber


class AddCardToWalletFragment : Fragment() {
    
    companion object {
        fun newInstance() =
            AddCardToWalletFragment()
    }
    
    private lateinit var viewModel: AddCardToWalletViewModel
    private lateinit var editCardNameView: EditText
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        
        return inflater.inflate(R.layout.fragment_add_card_to_wallet, container, false)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val application = requireNotNull(activity).application
        
        editCardNameView = new_card_name
        
        // Card value passed in through Fragment as a string
        val cardToChange = AddCardToWalletFragmentArgs.fromBundle(requireArguments()).cardToEdit
        Timber.i("I came from: $cardToChange")
        
        // The ViewModelFactory that takes the card name string and creates a ViewModel
        // with the card name string
        val viewModelFactory = AddCardToWalletViewModelFactory(cardToChange, application)
        
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(AddCardToWalletViewModel::class.java)
        
        // If we are coming from another card, set this to true
        var isValuePassed: Boolean = false
        
        // If the user selects to add a new card, the default value passed is an empty string,
        // so only have the text set if the user is coming from a card
        if (!cardToChange.matches("\\s*".toRegex())) {
            isValuePassed = true
            editCardNameView.setText(cardToChange)
        }
        
        var cardToBeEntered = editCardNameView.text.toString()
        
        val cardNew = Card(cardToBeEntered)
        
        // Check if text is empty
        // If empty, do nothing
        // If there is text, then start coroutine to load into database
        add_card_done.setOnClickListener {
            when {
                TextUtils.isEmpty(editCardNameView.text) or
                    editCardNameView.text.toString().trim().matches("\\s*".toRegex()) -> {
                    Toast.makeText(
                        context,
                        "Card not saved because it is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                isValuePassed -> {
                    // Edit the card
                    if (cardToBeEntered != cardToChange) {
                        cardToBeEntered.let {
                            viewModel.edit(cardToBeEntered, cardToChange)
                        }
                    }
                    cardToBeEntered = editCardNameView.text.toString()
//                    val action =
//                        AddCardToWalletFragmentDirections.actionAddCardToWalletFragmentToCardDetailsFragment(
//                            cardToBeEntered
//                        )
//                    findNavController().navigate(action)
                    
                    // Once value is added into the database, go back to the wallet
                    // This is only to check that the values have been updated.
                    val action =
                        AddCardToWalletFragmentDirections.actionAddCardToWalletFragmentToNavigationWallet()
                    findNavController().navigate(action)
                    
                    //TODO: Find a way to update the Wallet when card is updated
                
                }
                else -> {
                    cardToBeEntered = editCardNameView.text.toString()
                    // Insert a new card
                    cardToBeEntered.let {
                        val card = Card(it)
                        viewModel.insert(card)
                    }
    
                    // Once value is added into the database, go back to the wallet
                    val action =
                        AddCardToWalletFragmentDirections.actionAddCardToWalletFragmentToNavigationWallet()
                    findNavController().navigate(action)
                }
            }
        }
    }
}

