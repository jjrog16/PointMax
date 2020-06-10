package com.example.android.pointmax


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.ActionMenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android.pointmax.ui.wallet.WalletFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Plant tree to enable Debugging with Timber
        Timber.plant(Timber.DebugTree())
        
        
        // Find the bottomNavigation bar
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        
        // Find the fragment that will host the different fragments
        val navController = findNavController(R.id.nav_host_fragment)
        
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_wallet, R.id.navigation_recommended
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    
        // Navigate to AddCardFragment
        walletFAB.setOnClickListener {
            val action = WalletFragmentDirections.actionNavigationWalletToAddCardFragment()
            navController.navigate(action)
        }
        
        // Set the visibility of the navigation bar and the FAB
        navController.addOnDestinationChangedListener{_, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> {
                    navView.visibility = View.VISIBLE
                    walletFAB.visibility = View.GONE
                }
                R.id.navigation_wallet -> {
                    navView.visibility = View.VISIBLE
                    walletFAB.visibility = View.VISIBLE
                }
                R.id.navigation_recommended -> {
                    navView.visibility = View.VISIBLE
                    walletFAB.visibility = View.GONE
                }
                R.id.addCustomCardFragment -> {
                    navView.visibility = View.GONE
                    walletFAB.visibility = View.GONE
                }
                R.id.addCardFragment -> {
                    navView.visibility = View.GONE
                    walletFAB.visibility = View.GONE
                }
            }
        }
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item) {
            is ActionMenuItem -> {
                findNavController(R.id.nav_host_fragment).navigateUp()
            }
        }
        Timber.i("$item pressed")
        return super.onOptionsItemSelected(item)
    }
}
