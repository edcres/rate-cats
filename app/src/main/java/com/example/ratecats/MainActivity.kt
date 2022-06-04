package com.example.ratecats

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat

/** todo:
 * - launcher icon
 * - dataBinding
 * - recyclerview
 * - basic db queries
 * - Bottom navigation
 *      - set up navigation actions between fragments
 * - categories
 * - gifs
 * - favourites
 */

/**
 * tabs:
 * - favorites
 * - categories
 * - gifs
 * - all
 */

/** Maybe future:
 * - Upload feature and upload tab.
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.black)
        }
    }
}