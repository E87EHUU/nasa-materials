package com.example.nasa_materials.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nasa_materials.R
import com.example.nasa_materials.databinding.ActivityMainBinding
import com.example.nasa_materials.repository.ShredPrefSave
import com.example.nasa_materials.ui.fragments.crash.CrashFragment
import com.example.nasa_materials.ui.fragments.notes.NotesFragment
import com.example.nasa_materials.ui.fragments.pictureday.PictureOfTheDayFragment
import com.example.nasa_materials.ui.fragments.planets.ViewPagerFragment
import com.example.nasa_materials.ui.fragments.settings.SettingsFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            doFragmentNavigate(PictureOfTheDayFragment())
        }
        initThemePref()
        itemMenuSelect()
    }

    private fun itemMenuSelect() {
        binding.bottomAppBar.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.app_bar_settings -> {
                    doFragmentNavigate(SettingsFragment())
                }
                R.id.action_view_planets -> {
                    doFragmentNavigate(ViewPagerFragment())
                }
                R.id.app_bar_crash -> {
                    doFragmentNavigate(CrashFragment())
                }
                R.id.app_bar_notes -> {
                    doFragmentNavigate(NotesFragment())
                }
                else -> {
                    doFragmentNavigate(PictureOfTheDayFragment())
                }
            }
            true
        })
    }

    private fun doFragmentNavigate(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom,
                androidx.appcompat.R.anim.abc_slide_out_bottom)
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun initThemePref() {
        val themeStorage = ShredPrefSave(this.application)
        themeStorage.themeID.let {
            when (it) {
                0 -> {
                    setTheme(R.style.BaseTheme)
                    window.statusBarColor = getColor(R.color.colorPrimary)
                }
                1 -> {
                    setTheme(R.style.GreenTheme)
                    window.statusBarColor = getColor(R.color.greenPrimaryColor)
                }
                2 -> {
                    setTheme(R.style.SandTheme)
                    window.statusBarColor = getColor(R.color.sandPrimaryColor)
                }
            }
        }
    }
}