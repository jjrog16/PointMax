package com.example.android.pointmax.ui.wallet

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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
    
    private lateinit var viewModel: WalletViewModel
    private lateinit var editCardNameView: EditText
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_card_to_wallet, container, false)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        editCardNameView = new_card_name
        viewModel = ViewModelProvider(this).get(WalletViewModel::class.java)
    
        // Check if text is empty
        // If empty, do nothing
        // If there is text, then start coroutine to load into database
        add_card_done.setOnClickListener{
            if(TextUtils.isEmpty(editCardNameView.text)) {
                Toast.makeText(
                    context,
                    "Card not saved because it is empty",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val cardToBeEntered = editCardNameView.text.toString()
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
