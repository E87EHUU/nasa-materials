package com.example.nasa_materials.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.nasa_materials.R
//import com.example.nasa_materials.databinding.FragmentFirstBinding
import com.example.nasa_materials.databinding.FragmentPictureBinding
import com.example.nasa_materials.viewmodel.AppState
import com.example.nasa_materials.viewmodel.PictureOfTheDayViewModel

class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPictureBinding.inflate(inflater, container, false)
        return binding.root

    }

    val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(
            viewLifecycleOwner
        ) { appState ->
            renderData(appState)
        }
        viewModel.sendRequest()

        binding.chipToday.setOnClickListener {
            Toast.makeText(requireContext(),"Today",Toast.LENGTH_SHORT).show()
        }
        binding.chipYesterday.setOnClickListener {
            Toast.makeText(requireContext(),"Yesterday",Toast.LENGTH_SHORT).show()}
        binding.chipDayBeforeYesterday.setOnClickListener {
            Toast.makeText(requireContext(),"Day Before Yesterday",Toast.LENGTH_SHORT).show()}

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {/*TODO HW*/
            }
            AppState.Loading -> {/*TODO HW*/
            }
            is AppState.Success -> {
                binding.imageView.load(appState.pictureOfTheDayResponseData.url) {
                    //TODO HW настроить загрузку изображения:                    error()                    placeholder()
                }
            }
        }
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}