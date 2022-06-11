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
    }
}