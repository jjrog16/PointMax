package com.example.android.pointmax.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.pointmax.CardAdapter
import com.example.android.pointmax.MainActivity
import com.example.android.pointmax.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_wallet.*
import kotlinx.android.synthetic.main.fragment_wallet.view.*
import timber.log.Timber

class WalletFragment : Fragment() {
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: WalletViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_wallet, container, false)
        recyclerView = rootView.wallet_recyclerview
        val linearLayoutManager = LinearLayoutManager(context)
        viewManager = linearLayoutManager
        return rootView
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WalletViewModel::class.java)
        
        // Adapter that holds the current fragment context
        val adapter = activity?.let { CardAdapter(it) }
        wallet_recyclerview.adapter = adapter
    
        // Observe the ViewModel
        viewModel.allCards.observe(viewLifecycleOwner, Observer { cards ->
            cards?.let { adapter?.setCards(it) }
        })
    }
}
