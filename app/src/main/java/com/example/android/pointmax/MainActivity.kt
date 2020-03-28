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

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        Timber.i("Before when call: ${item}")
        when (item.itemId) {
            R.id.navigation_home-> {
                Timber.i("Home call: ${item}")
                replaceFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_wallet -> {
                Timber.i("Wallet call: ${item}")
                replaceFragment(WalletFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_recommended -> {
                Timber.i("Recommended call: ${item}")
                replaceFragment(RecommendedFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Plant tree to enable Debugging with Timber
        Timber.plant(Timber.DebugTree())

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_wallet, R.id.navigation_recommended))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragment(HomeFragment())

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment,fragment)
            .commit()
    }
}
