package ru.test.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import ru.test.features.ui.home.HomeViewModel
import ru.test.presentation.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val navView: BottomNavigationView = binding.navView

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_search,
                R.id.navigation_favourites,
                R.id.navigation_responses,
                R.id.navigation_messages,
                R.id.navigation_profile
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // контроль видимости toolBar в зависимости от пути навигации
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_search) {
                toolbar.visibility = View.GONE
            } else {
                toolbar.visibility = View.VISIBLE
            }
        }

        // установка количества избранных ваканский на значок в bottomNavigation
        viewModel.favouriteVacancies.observe(this) { vacancies ->
            val badgeDrawable = navView.getOrCreateBadge(R.id.navigation_favourites)
            if (vacancies.isNotEmpty()) {
                badgeDrawable.number = vacancies.size
                badgeDrawable.isVisible = true
            } else {
                badgeDrawable.isVisible = false
            }
        }
    }
}
