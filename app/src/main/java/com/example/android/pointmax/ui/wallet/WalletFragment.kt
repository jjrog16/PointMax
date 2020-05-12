package com.example.android.pointmax.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
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
import kotlinx.android.synthetic.main.recyclerview_item.*
import timber.log.Timber
import java.lang.Exception
import com.example.android.pointmax.databinding.FragmentWalletBinding

class WalletFragment : Fragment() {
    /**
     * Lazily initialize our [WalletViewModel]
     */
    private val viewModel: WalletViewModel by lazy {
        ViewModelProvider(this).get(WalletViewModel::class.java)
    }
    
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
    
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
    
        // Giving the binding access to the WalletViewModel
        binding.viewModel = viewModel
    
        // Sets the adapter of the wallet_recyclerview RecyclerView with clickHandler lambda that
        // tells the viewModel when our card is clicked
        binding.walletRecyclerview.adapter = CardAdapter(CardAdapter.OnClickListener {
            //viewModel.displayCardDetails(it)
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
