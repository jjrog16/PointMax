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
        val application = requireNotNull(activity).application
        
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
            Toast.makeText(context, "Selected Item: " + item.title, Toast.LENGTH_SHORT).show()
            viewModel.allCards.observe(viewLifecycleOwner, Observer {cardList ->
                when (item.itemId) {
                    R.id.generalCategory -> {
                        val bestCardList = mutableMapOf<String, Double>()
                        for(card in cardList){ bestCardList[card.cardName] = card.general }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.groceriesCategory -> {
                        val bestCardList = mutableMapOf<String, Double>()
                        for(card in cardList){ bestCardList[card.cardName] = card.groceries }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.restaurantsCategory -> {
                        val bestCardList = mutableMapOf<String, Double>()
                        for(card in cardList){ bestCardList[card.cardName] = card.restaurants }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.gasCategory -> {
                        val bestCardList = mutableMapOf<String, Double>()
                        for(card in cardList){ bestCardList[card.cardName] = card.gas }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.airlinesCategory -> {
                        val bestCardList = mutableMapOf<String, Double>()
                        for(card in cardList){ bestCardList[card.cardName] = card.airlines }
                        if (bestCardList.isNotEmpty()) {
                            val sorted = bestCardList.entries.sortedBy { it.value }
                            top_card.text = sorted.last().key
                        }
                        result = true
                    }
                    R.id.travelCategory -> {
                        val bestCardList = mutableMapOf<String, Double>()
                        for(card in cardList){ bestCardList[card.cardName] = card.travel }
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
