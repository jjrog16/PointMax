package com.example.android.pointmax.ui.add_new_card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.android.pointmax.R
import kotlinx.android.synthetic.main.fragment_add_card.*

class AddCardFragment : Fragment() {
    
    companion object {
        fun newInstance() = AddCardFragment()
    }
    
    private lateinit var viewModel: AddCardViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_card, container, false)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddCardViewModel::class.java)
        // TODO: Use the ViewModel
        
        add_own_card.setOnClickListener {
            val action = AddCardFragmentDirections.actionAddCardFragmentToAddCustomCardFragment("")
            findNavController().navigate(action)
        }
    }
    
}
