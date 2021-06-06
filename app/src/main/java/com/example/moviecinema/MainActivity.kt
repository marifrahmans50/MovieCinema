package com.example.moviecinema

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moviecinema.databinding.ActivityMainBinding
import com.example.moviecinema.ui.home.HomeFragment
import com.example.moviecinema.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

    private fun init() {
        binding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_search -> {
                    navigateChangeFragment(SearchFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_home -> {
                    navigateChangeFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_favorite -> {
                    moveToFavoriteFragment()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
        openFragment()
    }

    private fun openFragment() {
        binding.navView.selectedItemId = R.id.navigation_search
    }

    private fun navigateChangeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun moveToFavoriteFragment() {
        val fragment = instantiateFragment(classNameFavorite)
        if (fragment != null) {
            navigateChangeFragment(fragment)
        }
    }

    private val classNameFavorite: String
        get() = "com.example.moviecinema.favorite.favorite.FavoriteFragment"

    private fun instantiateFragment(classNameFavorite: String): Fragment? {
        return try {
            Class.forName(classNameFavorite).newInstance() as Fragment
        } catch (e: Exception) {
            Toast.makeText(this, "Module not Found", Toast.LENGTH_SHORT).show()
            null
        }
    }
}