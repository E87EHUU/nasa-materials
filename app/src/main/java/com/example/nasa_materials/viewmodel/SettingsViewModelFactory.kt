package com.example.nasa_materials.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasa_materials.model.repository.ShredPrefSave

class SettingsViewModelFactory(
    private val themeStorage: ShredPrefSave
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        SettingsViewModel(themeStorage) as T
}