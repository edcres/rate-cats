package com.example.ratecats.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.example.ratecats.R

/** todo:
 * - Debug UI
 *      - small loading icon
 *
 * - Bottom navigation
 *      - set up navigation actions between fragments
 * - Transition animations
 * - gifs
 * - favourites
 * - take out the categories view
 *
 * - Like pictures in the API and show which ones are liked
 * - figure out how to work with the API key (and if I even need it)
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
 * - probably make it a gridlayout manager.
 * - Upload feature and upload tab.
 * - animations for the thumb up and thumb down and the others.
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