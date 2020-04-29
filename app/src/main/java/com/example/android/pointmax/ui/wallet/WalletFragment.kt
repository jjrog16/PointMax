package com.example.android.pointmax.ui.wallet

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.pointmax.CardAdapter
import com.example.android.pointmax.R
import kotlinx.android.synthetic.main.fragment_wallet.*
import kotlinx.android.synthetic.main.fragment_wallet.view.*
import timber.log.Timber

class WalletFragment : Fragment() {
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: WalletViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        val rootView = inflater.inflate(R.layout.fragment_wallet, container, false)
        recyclerView = rootView.wallet_recyclerview
        
        viewModel = ViewModelProvider(
            this
        ).get(WalletViewModel::class.java)
        
    
        return rootView
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wallet_recyclerview.setBackgroundColor(Color.BLUE)
    
        val linearLayoutManager = LinearLayoutManager(context)
        viewManager = linearLayoutManager
    
        // Observe the ViewModel
        viewModel.allCards.observe(viewLifecycleOwner, Observer { cards ->
            cards?.let { CardAdapter(cards).setCards(it) }
        })
    }
}
