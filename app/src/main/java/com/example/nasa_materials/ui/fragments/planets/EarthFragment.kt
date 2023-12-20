package com.example.nasa_materials.ui.fragments.planets

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nasa_materials.databinding.FragmentEarthBinding
import com.example.nasa_materials.utils.DURATION_FOR_EARTH

class EarthFragment : Fragment() {

    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rotatePicture()
    }

    /**Здесь задействовал анимацию также. При нажатии на картинку,
    она вращается. Такое же реализовано и для остальных фрагментов из ViewPager*/
    private fun rotatePicture(){
        binding.earthImage.setOnClickListener {
            ObjectAnimator.ofFloat(binding.earthImage, View.ROTATION, 0f, 360f)
                .setDuration(DURATION_FOR_EARTH).start()
        }
    }
}