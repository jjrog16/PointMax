package com.example.android.pointmax


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android.pointmax.database.Card
import com.example.android.pointmax.ui.AddCardToWallet.AddCardToWalletFragment
import com.example.android.pointmax.ui.wallet.WalletFragmentDirections
import com.example.android.pointmax.ui.wallet.WalletViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val newCardActivityRequestCode = 1
    private lateinit var walletViewModel: WalletViewModel
    
    // If the activity returns with RESULT_OK, insert the returned word into the database
    // by calling the insert() method of the WordViewModel.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (requestCode == newCardActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(AddCardToWalletFragment.EXTRA_REPLY)?.let {
                val card = Card(it)
                walletViewModel.insert(card)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
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
    
        // Navigate to AddCardToWalletFragment
        walletFAB.setOnClickListener {
            val action = WalletFragmentDirections.actionNavigationWalletToAddCardToWalletFragment()
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
                R.id.addCardToWalletFragment -> {
                    navView.visibility = View.GONE
                    walletFAB.visibility = View.GONE
                }
            }
        }
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController(R.id.nav_host_fragment).navigateUp()
        return super.onOptionsItemSelected(item)
    }
}
