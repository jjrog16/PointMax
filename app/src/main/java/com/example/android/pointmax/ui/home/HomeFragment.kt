package com.example.android.pointmax.ui.home

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.pointmax.R
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayDeque

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
        
        fun getBestCard(categoryName: String) : String {
            val result = mutableListOf<String>()
            
            return result[0]
        }
        
        category_button.setOnClickListener {
            val popupMenu = PopupMenu(context, category_button)
            val menuInflater: MenuInflater = popupMenu.menuInflater
            popupMenu.setOnMenuItemClickListener(this)
            menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.show()
            
        }
    }
    
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        
        if (item != null) {
            val category = item.title.toString().toLowerCase()
            Toast.makeText(context, "Selected Item: " + item.title, Toast.LENGTH_SHORT).show()
            when (item.itemId) {
                R.id.generalCategory -> {
                    return true
                }
                R.id.groceriesCategory -> {
                    return true
                }
                R.id.restaurantsCategory -> {
                    return true
                }
                R.id.gasCategory -> {
                    return true
                }
                R.id.airlinesCategory -> {
                    return true
                }
                R.id.travelCategory -> {
                    return true
                }
            }
        }
        return false
    }
}
