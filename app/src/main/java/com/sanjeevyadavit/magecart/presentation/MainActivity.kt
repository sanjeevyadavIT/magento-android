package com.sanjeevyadavit.magecart.presentation

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sanjeevyadavit.magecart.R
import com.sanjeevyadavit.magecart.databinding.ActivityMainBinding
import com.sanjeevyadavit.magecart.presentation.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    companion object {
        const val SHARED_PREFERENCE_NAME = "myAppPrefs"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        sharedPref = getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            MODE_PRIVATE
        )
        getNavController()
        setupBottomNavigation()
        setupToolbar()
        setupDrawer()
    }

    private fun getNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setupToolbar() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.categoriesFragment,
                R.id.profileFragment
            ), binding.drawerContainer
        )
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

    }

    private fun setupDrawer() {
        binding.drawerView.setupWithNavController(navController)
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.categoriesFragment, R.id.profileFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigation.visibility = View.VISIBLE
        binding.bottomNavigation.setOnItemSelectedListener {
            navController.navigate(
                when(it.itemId){
                    R.id.homeFragment -> R.id.homeFragment
                    R.id.categoriesFragment -> R.id.categoriesFragment
                    R.id.profileFragment -> if(isLoggedIn()) R.id.profileFragment else R.id.loginFragment
                    else -> R.id.homeFragment
                }
            )
            return@setOnItemSelectedListener true
        }
    }

    private fun hideBottomNav() {
        binding.bottomNavigation.visibility = View.GONE
    }

    private fun isLoggedIn() = sharedPref.getString(LoginFragment.CUSTOMER_TOKEN, null) != null
}