package com.sanjeevyadavit.magecart.presentation

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sanjeevyadavit.magecart.R
import com.sanjeevyadavit.magecart.databinding.ActivityMainBinding
import com.sanjeevyadavit.magecart.presentation.cart.CartViewModel
import com.sanjeevyadavit.magecart.presentation.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels() // QUESTION: Is it a good practice to have cart view model here as activity scope and when logged in call it's function to fetch cart
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
        fetchCustomerCart()
        setupObservers()
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
                R.id.profileFragment,
                R.id.cartFragment
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
                R.id.homeFragment, R.id.categoriesFragment, R.id.profileFragment, R.id.cartFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigation.visibility = View.VISIBLE
        binding.bottomNavigation.setOnItemSelectedListener {
            navController.navigate(
                when (it.itemId) {
                    R.id.homeFragment -> R.id.homeFragment
                    R.id.categoriesFragment -> R.id.categoriesFragment
                    R.id.profileFragment -> if (isLoggedIn()) R.id.profileFragment else R.id.loginFragment
                    R.id.cartFragment -> if (isLoggedIn()) R.id.cartFragment else R.id.loginFragment
                    else -> R.id.homeFragment
                }
            )
            return@setOnItemSelectedListener true
        }
    }

    private fun hideBottomNav() {
        binding.bottomNavigation.visibility = View.GONE
    }

    fun getCustomerToken() = sharedPref.getString(LoginFragment.CUSTOMER_TOKEN, null)

    fun isLoggedIn() = getCustomerToken() != null

    // TODO: Instead of reading token again and again directly from Sharedpreference save inb this file and update it on change
    fun fetchCustomerCart(customerToken: String? = null, firstTimeLoggedIn: Boolean = false) = (customerToken ?: getCustomerToken())?.let { token ->
        cartViewModel.fetchCustomerCart(
            token,
            firstTimeLoggedIn
        )
    }

    private fun setupObservers() {
        // QUESTION: Is there any better way to ovserve State from CartViewModel
        // FIXME: Number is not showing, just a red dot is showing
        val cartTab = binding.bottomNavigation.menu.findItem(R.id.cartFragment)
        val badgeDrawable = binding.bottomNavigation.getOrCreateBadge(cartTab.itemId)
        MutableStateFlow(cartViewModel.cart.value).asLiveData().observe(this) {
            it?.itemsQuantity?.let { qty ->
                badgeDrawable.number = qty
            }
        }
    }

}