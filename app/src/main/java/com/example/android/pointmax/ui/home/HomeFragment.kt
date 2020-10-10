package com.example.android.pointmax.ui.home

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.pointmax.R
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class HomeFragment : Fragment(), PopupMenu.OnMenuItemClickListener  {
    
    private lateinit var viewModel: HomeViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        
        category_button.setOnClickListener {
            val popupMenu = PopupMenu(context, category_button)
            val menuInflater: MenuInflater = popupMenu.menuInflater
            popupMenu.setOnMenuItemClickListener(this)
            menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.show()
            
        }
    }
    
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        var result = false
        if (item != null) {
            viewModel.allCards.observe(viewLifecycleOwner, Observer {cardList ->
                val bestCardList = mutableMapOf<String, Double>()
                if(cardList.isEmpty()) {
                    Toast.makeText(context, "No cards in wallet", Toast.LENGTH_SHORT).show()
                    return@Observer
                } else {
                    bestCardList.clear()
                }
                when (item.itemId) {
                    R.id.generalCategory -> {
                        cardList.forEach {
                            bestCardList[it.cardName] = it.general
                        }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.groceriesCategory -> {
                        cardList.forEach {
                            bestCardList[it.cardName] = it.groceries
                        }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.restaurantsCategory -> {
                        cardList.forEach {
                            bestCardList[it.cardName] = it.restaurants
                        }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.gasCategory -> {
                        cardList.forEach {
                            bestCardList[it.cardName] = it.gas
                        }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.airlinesCategory -> {
                        cardList.forEach {
                            bestCardList[it.cardName] = it.airlines
                        }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.travelCategory -> {
                        cardList.forEach {
                            bestCardList[it.cardName] = it.travel
                        }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            top_card.text = sorted.last().key
                        }
                        result = true
                    }
                }
            })
        }
        return result
    }
}
