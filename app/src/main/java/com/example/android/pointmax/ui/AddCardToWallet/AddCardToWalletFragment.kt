package com.example.android.pointmax.ui.AddCardToWallet

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.android.pointmax.R

class AddCardToWalletFragment : Fragment() {
    
    companion object {
        fun newInstance() = AddCardToWalletFragment()
    }
    
    private lateinit var viewModel: AddCardToWalletViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_card_to_wallet, container, false)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddCardToWalletViewModel::class.java)
        // TODO: Use the ViewModel
    }
    
}