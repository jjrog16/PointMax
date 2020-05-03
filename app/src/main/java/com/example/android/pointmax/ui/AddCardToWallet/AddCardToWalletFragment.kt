package com.example.android.pointmax.ui.AddCardToWallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.pointmax.MainActivity
import com.example.android.pointmax.R
import timber.log.Timber


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
        viewModel = ViewModelProvider(this).get(AddCardToWalletViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
