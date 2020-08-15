package com.example.android.pointmax.ui.add_custom_card

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
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
        
        // Set the text of the EditText view only if coming from another card
        val isComingFromAnotherCard = setDefaultEditText(cardToChange)
        
        // The ViewModelFactory that takes the card name string and creates a ViewModel
        // with the card name string
        val viewModelFactory = AddCustomCardViewModelFactory(cardToChange, application)
        
        // Create ViewModel with ViewModelFactory
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(AddCustomCardViewModel::class.java)
        
        viewModel.allCards.observe(viewLifecycleOwner, Observer { cardList ->
            if (cardList != null) {
                var cardToBeEntered = editCardNameView.text.toString().trim().toUpperCase()
                setCategoryViews(cardList, cardToBeEntered)
                val valuesBeforeDonePressed = arrayOf(
                    generalEarn.text.toString().toDouble(),
                    airlinesEarn.text.toString().toDouble(),
                    restaurantsEarn.text.toString().toDouble(),
                    groceriesEarn.text.toString().toDouble(),
                    travelEarn.text.toString().toDouble(),
                    gasEarn.text.toString().toDouble()
                )
                
                add_card_done.setOnClickListener {
                    cardToBeEntered = editCardNameView.text.toString().trim().toUpperCase()
                    val valuesAfterDonePressed = arrayOf(
                        generalEarn.text.toString().toDouble(),
                        airlinesEarn.text.toString().toDouble(),
                        restaurantsEarn.text.toString().toDouble(),
                        groceriesEarn.text.toString().toDouble(),
                        travelEarn.text.toString().toDouble(),
                        gasEarn.text.toString().toDouble()
                    )
                    
                    // Check to see if user changed any value in the EditText after pressing done
                    fun isBeforeAndAfterValueEqual(): Boolean = valuesBeforeDonePressed.contentEquals(valuesAfterDonePressed)
                    
                    when {
                        // Case when nothing is in EditText
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
                            // Save the edited card only if the card entered is
                            // not the same as a card already in the database
                            if (!isCardInList(cardList,cardToBeEntered) || (isCardInList(cardList,cardToBeEntered) && !isBeforeAndAfterValueEqual())) {
                                cardToBeEntered.let {
                                    viewModel.deleteByName(cardToChange)
                                    viewModel.insert(createCard(it))
                                }
                                // Go back to wallet after finishing the edit
                                val action =
                                    AddCustomCardFragmentDirections.actionAddCustomCardFragmentToNavigationWallet()
                                findNavController().navigate(action)
            
                                // Hides keyboard after finishing input
                                context?.let { it1 -> hideKeyboard(it1, editCardNameView) }
                            } else {
                                // Go back to wallet if no changes made 
                                val action =
                                    AddCustomCardFragmentDirections.actionAddCustomCardFragmentToNavigationWallet()
                                findNavController().navigate(action)
                            }
                        }
                        else -> {
                            // Adding a new card into the Wallet
                            if (!isCardInList(cardList,cardToBeEntered)) {
                                // Insert a new card
                                cardToBeEntered.let {
                                    viewModel.insert(createCard(it))
    
                                    // Once value is added into the database, go back to the wallet
                                    val action =
                                        AddCustomCardFragmentDirections.actionAddCustomCardFragmentToNavigationWallet()
                                    findNavController().navigate(action)
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    getString(R.string.card_already_exists),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            // Hides keyboard after finishing input
                            context?.let { it1 -> hideKeyboard(it1, editCardNameView) }
                        }
                    }
                }
                
                delete_button.setOnClickListener {
                    cardToBeEntered.let {
                        viewModel.deleteByName(it)
                        
                        val action = AddCustomCardFragmentDirections.actionAddCustomCardFragmentToNavigationWallet()
                        findNavController().navigate(action)
                    }
                }
            }
        })
    }
    // Called after the operation is completed in order to hide the keyboard when EditText field
    // is gone.
    private fun hideKeyboard(context: Context, editText: EditText) {
        val imm: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
    
    // If the user selects to add a new card, the default value passed is an empty string,
    // so only have the text set if the user is coming from a card
    private fun setDefaultEditText(cardToChange: String): Boolean {
        // Are we coming from another card?
        var isValuePassed = false
        
        if (!cardToChange.matches("\\s*".toRegex())) {
            isValuePassed = true
            editCardNameView.setText(cardToChange)
        }
        return isValuePassed
    }
    
    // Set the category earn rates to the appropriate card
    private fun setCategoryViews(cardList: List<Card>, cardToBeEntered: String) {
        for (card in cardList) {
            if (card.cardName == cardToBeEntered) {
                generalEarn.setText(card.general.toString())
                airlinesEarn.setText(card.airlines.toString())
                restaurantsEarn.setText(card.restaurants.toString())
                travelEarn.setText(card.travel.toString())
                groceriesEarn.setText(card.groceries.toString())
                gasEarn.setText(card.gas.toString())
            }
        }
    }
    
    // Check to see if a card is in the List
    private fun isCardInList(cardList: List<Card>, cardToBeEntered: String): Boolean {
        for (card in cardList) if (card.cardName == cardToBeEntered) return true
        return false
    }
    
    // Creates a card based off the values entered on the view
    private fun createCard(cardToBeEntered: String) : Card {
        return if(generalEarn.text.toString().toDouble() > 1.0) {
            Timber.i("Card to be entered is = $cardToBeEntered")
            // Assign all categories to the general value
            Card(
                cardToBeEntered,
                general = generalEarn.text.toString().toDouble(),
                airlines = generalEarn.text.toString().toDouble(),
                restaurants = generalEarn.text.toString().toDouble(),
                groceries = generalEarn.text.toString().toDouble(),
                travel = generalEarn.text.toString().toDouble(),
                gas = generalEarn.text.toString().toDouble()
            )
        } else {
            // Assign the values as the user submits
             Card(
                cardToBeEntered,
                general = generalEarn.text.toString().toDouble(),
                airlines = airlinesEarn.text.toString().toDouble(),
                restaurants = restaurantsEarn.text.toString().toDouble(),
                groceries = groceriesEarn.text.toString().toDouble(),
                travel = travelEarn.text.toString().toDouble(),
                gas = gasEarn.text.toString().toDouble()
            )
        }
    }
}




