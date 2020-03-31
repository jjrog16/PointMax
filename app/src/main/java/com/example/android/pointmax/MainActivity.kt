package com.example.android.pointmax


import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android.pointmax.ui.home.HomeFragment
import com.example.android.pointmax.ui.recommended.RecommendedFragment
import com.example.android.pointmax.ui.wallet.WalletFragment
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
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_wallet, R.id.navigation_recommended))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
