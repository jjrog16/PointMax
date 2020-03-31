package com.example.android.pointmax.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.pointmax.databinding.FragmentHomeBinding
import timber.log.Timber

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = ViewModelProvider(
            this).get(HomeViewModel::class.java)
        return binding.root
    }
}
