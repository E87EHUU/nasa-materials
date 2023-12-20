package com.example.nasa_materials.ui.fragments.planets

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nasa_materials.databinding.FragmentSystemBinding
import com.example.nasa_materials.utils.DURATION_FOR_SYSTEM

class SystemFragment : Fragment() {

    private var _binding: FragmentSystemBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSystemBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rotatePicture()
    }

    /**Здесь задействовал анимацию также. При нажатии на картинку,
    она вращается. Такое же реализовано и для остальных фрагментов из ViewPager*/
    private fun rotatePicture(){
        binding.systemPicture.setOnClickListener {
            ObjectAnimator.ofFloat(binding.systemPicture, View.ROTATION, 0f, 1800f)
                .setDuration(DURATION_FOR_SYSTEM).start()
        }
    }
}