package com.example.nasa_materials.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.nasa_materials.databinding.FragmentSettingsBinding
import com.example.nasa_materials.model.repository.ShredPrefSave
import com.example.nasa_materials.viewmodel.SettingsViewModel
import com.example.nasa_materials.viewmodel.SettingsViewModelFactory

class SettingsFragment : Fragment() {

    private val THEME_BASE = 0
    private val THEME_GREEN = 1
    private val THEME_SAND = 2

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val settingsViewModel: SettingsViewModel by viewModels {
        SettingsViewModelFactory(
            ShredPrefSave(requireActivity().application)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            themeBaseButton.setOnClickListener {
                settingsViewModel.setTheme(THEME_BASE)
                requireActivity().recreate()
            }
            themeGreenButton.setOnClickListener {
                settingsViewModel.setTheme(THEME_GREEN)
                requireActivity().recreate()
            }
            themeSandButton.setOnClickListener {
                settingsViewModel.setTheme(THEME_SAND)
                requireActivity().recreate()
            }
        }
    }

    private fun init() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            settingsViewModel.theme.collect {
                when (it) {
                    THEME_BASE -> {
                        binding.themeBaseButton.isChecked = true
                    }
                    THEME_GREEN -> {
                        binding.themeGreenButton.isChecked = true
                    }
                    THEME_SAND -> {
                        binding.themeSandButton.isChecked = true
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        fun newInstance() = SettingsFragment()
    }
}