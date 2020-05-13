package com.example.android.pointmax.ui.recommended

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.pointmax.databinding.FragmentRecommendedBinding

class RecommendedFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRecommendedBinding = FragmentRecommendedBinding.inflate(layoutInflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = ViewModelProvider(
            this).get(RecommendedViewModel::class.java)
        return binding.root
    }
}
