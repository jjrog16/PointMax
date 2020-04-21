package com.example.android.pointmax.ui.wallet

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
import com.example.android.pointmax.databinding.FragmentWalletBinding

class WalletFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentWalletBinding = FragmentWalletBinding.inflate(layoutInflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = ViewModelProvider(
            this).get(WalletViewModel::class.java)
        return binding.root
    }
}
