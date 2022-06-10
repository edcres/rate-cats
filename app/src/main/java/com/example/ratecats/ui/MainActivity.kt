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
 * - favourites
 *      - DELETE old favorites
 *
 * - check each item in the ViewHolder to see if it is favorited ans set the appropriate image
 *          - check the map in the viewModel with .contains(key)
 *
 * - Improve the look of the cardView
 *
 * - Explore the breed attribute
 */

/** todo: bugs
 * If need to resize the image:
 *  - https://stackoverflow.com/questions/46114603/resize-images-with-glide-in-a-imageview-android
 * The categories view (and maybe other views) gets created more than once (maybe it has something to do with the bottom nav bar)
 *      - this causes a bug that I used a try catch to cover up.
 *      - also apparently causes the list to refresh with new images. Which I like but it's not on purpose.
 */

/** Maybe future:
 * - Have categories filter for all and gifs
 * - Refresh the views so new images show up
 * - Test if the connection is okay so the app doesn't crash.
 * - View transition animations
 * - probably make it a gridlayout manager.
 * - Upload feature and upload tab.
 * - animations for the thumb up and thumb down and the others.
 * - Make the images align better. Have a linear layout instead of the constraint layout,
 *      - and have a constraint layout for the imgBtns
 * - Use different sub_ids for different users.
 * - Have a like and dislike button
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
    }
}