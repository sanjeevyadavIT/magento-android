package com.sanjeevyadavit.magecart

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sanjeevyadavit.magecart.databinding.ActivityMainBinding
import com.sanjeevyadavit.magecart.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getNavController().let {
            setupToolbar(it)
            setupDrawer(it)
        }
    }

    private fun getNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        return navHostFragment.navController
    }

    private fun setupToolbar(navController: NavController) {
        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerContainer)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

    }

    private fun setupDrawer(navController: NavController) {
        binding.drawerView.setupWithNavController(navController)
    }
}