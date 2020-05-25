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
import timber.log.Timber

class WalletFragment : Fragment() {
    /**
     * Lazily initialize our [WalletViewModel].
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
            viewModel.displayCardDetails(it.card)
            Timber.i("Credit Card: $it")
        })
    
        // Observe the navigateToSelectedCard LiveData and Navigate when it isn't null
        // After navigating, call displayCardDetailsComplete() so that the ViewModel is ready
        // for another navigation event
        viewModel.navigateToSelectedCard.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                // Must find the NavController from the Fragment
                this.findNavController()
                    .navigate(WalletFragmentDirections.actionNavigationWalletToCardDetailsFragment(it.cardName))
                viewModel.displayCardDetailsComplete()
            }
        })
        return binding.root
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
    }
}
