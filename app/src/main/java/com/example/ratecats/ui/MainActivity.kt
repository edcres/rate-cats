package com.example.ratecats.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.ratecats.R
import com.google.android.material.bottomnavigation.BottomNavigationView


/** todo:
 * - gifs
 * - favourites
 * - take out the categories view
 *
 * - Like pictures in the API and show which ones are liked
 * - figure out how to work with the API key (and if I even need it)
 *
 * - filter categories in the all view
 *
 * - Explore the breed attribute
 */

/**
 * tabs:
 * - favorites
 * - gifs
 * - all
 *      - can filter categories
 */

/** todo: possible bugs
 * If need to resize the image:
 *  - https://stackoverflow.com/questions/46114603/resize-images-with-glide-in-a-imageview-android
 */

/** Maybe future:
 * - View transition animations
 * - probably make it a gridlayout manager.
 * - Upload feature and upload tab.
 * - animations for the thumb up and thumb down and the others.
 */

private const val TAG = "MainAct__TAG"

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.black)
        }
        // Bottom navigation
        bottomNavBar = findViewById(R.id.bottom_nav)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        if (navHostFragment != null) {
            NavigationUI.setupWithNavController(bottomNavBar, navHostFragment!!.navController)
        } else Log.e(TAG, "onCreate: navHostFragment is null")


//        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
//
//        bottomNavBar.setOnItemSelectedListener {
//            when(it.itemId) {
//                R.id.gifs_btn -> {
//                    navController.navigate(R.id.action_categoriesFragment_to_gifsFragment)
//                    return@setOnItemSelectedListener true
//                }
//                R.id.categories_btn -> {
//                    navController.navigate(R.id.action_favoritesFragment_to_categoriesFragment)
//                    return@setOnItemSelectedListener true
//                }
//                R.id.favorites_btn -> {
//                    navController.navigate(R.id.action_categoriesFragment_to_favoritesFragment)
//                    return@setOnItemSelectedListener true
//                }
//            }
//            false
//        }
    }
}