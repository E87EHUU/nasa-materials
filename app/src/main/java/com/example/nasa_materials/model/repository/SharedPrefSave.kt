package com.example.nasa_materials.model.repository

import android.content.Context

import com.example.nasa_materials.utils.KEY_THEME_ID
import com.example.nasa_materials.utils.SHARED_PREF_THEME


class ShredPrefSave(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREF_THEME,
        Context.MODE_PRIVATE
    )


    var themeID: Int
        get() = sharedPreferences.getInt(KEY_THEME_ID, 0)
        set(value) {
            sharedPreferences
                .edit()
                .putInt(KEY_THEME_ID, value)
                .apply()

        }

}