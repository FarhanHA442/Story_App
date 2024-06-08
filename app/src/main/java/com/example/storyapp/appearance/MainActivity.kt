package com.example.storyapp.appearance

import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.storyapp.R
import com.example.storyapp.base.BaseActivity
import com.example.storyapp.databinding.ActivityMainBinding
import com.example.storyapp.utils.bottomBarScope

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun assignBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initActivity() {
        super.initActivity()
        installSplashScreen()
    }

    override fun doSomething() {
        super.doSomething()
        setupBottomBar()
    }

    private fun setupBottomBar() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController: NavController = navHost.navController

        binding.bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (bottomBarScope.contains(destination.id)) {
                binding.bottomNavigationView.visibility = View.VISIBLE
            } else {
                binding.bottomNavigationView.visibility = View.GONE
            }
        }
    }
}