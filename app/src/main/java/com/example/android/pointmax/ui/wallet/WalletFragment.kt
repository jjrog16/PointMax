package com.example.android.pointmax.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.android.pointmax.CardAdapter

import com.example.android.pointmax.databinding.FragmentWalletBinding

class WalletFragment : Fragment() {
    
    
    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWalletBinding.inflate(inflater)
    
        val viewModel: WalletViewModel = ViewModelProvider(this).get(WalletViewModel::class.java)
        
    
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)
    
        // Giving the binding access to the WalletViewModel
        binding.viewModel = viewModel
    
        // Sets the adapter of the wallet_recyclerview RecyclerView with clickHandler lambda that
        // tells the viewModel when our card is clicked
        binding.walletRecyclerview.adapter = CardAdapter(CardAdapter.OnClickListener {
            viewModel.displayCardDetails(it)
        })
        
        // Observe the navigateToSelectedCard LiveData and Navigate when it isn't null
        // After navigating, call displayCardDetailsComplete() so that the ViewModel is ready
        // for another navigation event
        viewModel.navigateToSelectedCard.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(WalletFragmentDirections.actionNavigationWalletToCardDetailsFragment())
                viewModel.displayCardDetailsComplete()
            }
        })
        
        return binding.root
        
        
        
//        val rootView = inflater.inflate(R.layout.fragment_wallet, container, false)
//        recyclerView = rootView.wallet_recyclerview
//        val linearLayoutManager = LinearLayoutManager(context)
//        viewManager = linearLayoutManager
//        return rootView
    }
    
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(WalletViewModel::class.java)
//
//        // Adapter that holds the current fragment context
//        val adapter = activity?.let { CardAdapter(it) }
//        wallet_recyclerview.adapter = adapter
//
//        // Observe the ViewModel
//        viewModel.allCards.observe(viewLifecycleOwner, Observer { cards ->
//            cards?.let { adapter?.setCards(it) }
//        })
//
//
//
//    }
}
