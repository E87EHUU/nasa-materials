package com.example.nasa_materials.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.ui.AppBarConfiguration
import com.example.nasa_materials.R
import com.example.nasa_materials.databinding.ActivityMainBinding
import com.example.nasa_materials.model.repository.ShredPrefSave

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.container,
                PictureOfTheDayFragment.newInstance()
            ).commit()
        }
    }

    private fun init() {
        val themeStorage = ShredPrefSave(this.application)
        themeStorage.themeID.let {
            when (it) {
                0 -> setTheme(R.style.BaseTheme)
                1 -> setTheme(R.style.GreenTheme)
                2 -> setTheme(R.style.SandTheme)
            }
        }
    }
}