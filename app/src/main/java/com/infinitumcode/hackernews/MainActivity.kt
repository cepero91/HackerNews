package com.infinitumcode.hackernews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.infinitumcode.hackernews.databinding.ActivityMainBinding
import com.infinitumcode.hackernews.ui.main.MainFragment
import com.wada811.databinding.dataBinding

class MainActivity : AppCompatActivity(R.layout.activity_main),
    NavController.OnDestinationChangedListener {

    private val binding: ActivityMainBinding by dataBinding()
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_main
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when(destination.id){
            R.id.nav_splash -> hideAllBars()
            else -> showAllBars()
        }
    }

    private fun showAllBars() {
        binding.toolbar.isVisible = true
    }

    private fun hideAllBars() {
        binding.toolbar.isVisible = false
    }
}