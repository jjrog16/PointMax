package com.example.android.pointmax.ui.add_custom_card

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.pointmax.R
import com.example.android.pointmax.database.Card
import kotlinx.android.synthetic.main.fragment_add_custom_card.*
import timber.log.Timber


class AddCustomCardFragment : Fragment() {
    
    companion object {
        fun newInstance() =
            AddCustomCardFragment()
    }
    
    private lateinit var viewModel: AddCustomCardViewModel
    private lateinit var editCardNameView: EditText
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_custom_card, container, false)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val application = requireNotNull(activity).application
        
        // EditText field
        editCardNameView = new_card_name
        
        // Card value passed in through Fragment as a string
        val cardToChange = AddCustomCardFragmentArgs.fromBundle(requireArguments()).cardToEdit
        
        // Holds the entered value of EditText
        var cardToBeEntered: String
        
        // Set the text of the EditText view only if coming from another card
        val isComingFromAnotherCard = setDefaultEditText(cardToChange)
        
        
        
        // The ViewModelFactory that takes the card name string and creates a ViewModel
        // with the card name string
        val viewModelFactory = AddCustomCardViewModelFactory(cardToChange, application)
        
        // Create ViewModel with ViewModelFactory
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(AddCustomCardViewModel::class.java)
        
        // Check if text is empty
        // If empty, do nothing
        // If there is text, then start coroutine to load into database
        add_card_done.setOnClickListener {
            when {
                TextUtils.isEmpty(editCardNameView.text) or
                    editCardNameView.text.toString().trim().matches("\\s*".toRegex()) -> {
                    Toast.makeText(
                        context,
                        getString(R.string.empty_not_saved),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // Sets the text of the EditText view only if coming from another card
                isComingFromAnotherCard -> {
                    // Take new entered input
                    cardToBeEntered = editCardNameView.text.toString().trim()
    
                    val isCardToBeEnteredInDB = checkIfCardInList(cardToBeEntered)
                    Timber.i("Is the $cardToBeEntered in the db? -> $isCardToBeEnteredInDB")
                    
                    // Edit the card only if the card entered is not the same
                    if (!isCardToBeEnteredInDB) {
                        cardToBeEntered.let {
                            viewModel.edit(oldName = cardToChange, newName = cardToBeEntered)
                        }
    
                        // Go back to wallet after finishing the edit
                        val action = AddCustomCardFragmentDirections.actionAddCustomCardFragmentToNavigationWallet()
                        findNavController().navigate(action)
    
                        // Hides keyboard after finishing input
                        context?.let { it1 -> hideKeyboard(it1,editCardNameView) }
                    } else {
                        Toast.makeText(
                            context,
                            getString(R.string.card_already_exists),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                else -> {
                    // Take new entered input
                    cardToBeEntered = editCardNameView.text.toString().trim()
    
                    val isCardToBeEnteredInDB = checkIfCardInList(cardToBeEntered)
                    Timber.i("Is the $cardToBeEntered in the db? -> $isCardToBeEnteredInDB")
                    
                    if(!isCardToBeEnteredInDB) {
                        // Insert a new card
                        cardToBeEntered.let {
                            val card = Card(it)
                            viewModel.insert(card)
                        }
                    } else {
                        Toast.makeText(
                            context,
                            getString(R.string.card_already_exists),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
    
                    // Once value is added into the database, go back to the wallet
                    val action =
                        AddCustomCardFragmentDirections.actionAddCustomCardFragmentToNavigationWallet()
                    findNavController().navigate(action)
                    
                    // Hides keyboard after finishing input
                    context?.let { it1 -> hideKeyboard(it1,editCardNameView) }
                }
            }
        }
    }
    
    // Called after the operation is completed in order to hide the keyboard when EditText field
    // is gone.
    private fun hideKeyboard(context: Context, editText: EditText) {
        val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken,0)
    }
    
    
    private fun checkIfCardInList(cardToCheck: String): Boolean {
        
        var isCardInList = false
        viewModel.allCards.observe(viewLifecycleOwner, Observer {
            isCardInList = it.contains(Card(cardName = cardToCheck))
            Timber.i("All Cards: $it")
            Timber.i("Is $cardToCheck in $it? -> ${it.contains(Card(cardToCheck))}")
        })
        return isCardInList
    }
    
    private fun setDefaultEditText(cardToChange: String): Boolean {
        var isValuePassed = false
        
        // If the user selects to add a new card, the default value passed is an empty string,
        // so only have the text set if the user is coming from a card
        if (!cardToChange.matches("\\s*".toRegex())) {
            isValuePassed = true
            editCardNameView.setText(cardToChange)
        }
        return isValuePassed
    }
    
//    // Show visibility to the EditText views
//    fun addNewCategories(){
//
//        // Add the view into the screen when pressed
//        add_next_category.setOnClickListener {
//
//        }
//    }
//
//    fun populateSpinner(){
//        var arraySpinner = arrayOf("General", "Groceries", "Restaurant", "Gas", "Airlines", "Travel")
//    }
}

