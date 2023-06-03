package com.example.nasa_materials.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.example.nasa_materials.R
import com.example.nasa_materials.R.drawable
import com.example.nasa_materials.R.menu
import com.example.nasa_materials.databinding.FragmentPictureOfTheDayBinding
import com.example.nasa_materials.model.entities.PictureOfDayDTO
import com.example.nasa_materials.utils.URI_WIKI
import com.example.nasa_materials.utils.toast
import com.example.nasa_materials.viewmodel.AppState
import com.example.nasa_materials.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PictureOfTheDayFragment : Fragment() {

    private var isZoomed = false
    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding get() = _binding!!

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }

        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(URI_WIKI + binding.inputEditText.text.toString())
            })
        }
        setBottomAppBar(view)
        enlargePictureFirst()
        animatePhotoClick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> toast("Favourite")
            R.id.app_bar_settings -> activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.container, SettingsFragment.newInstance())
                ?.addToBackStack(null)
                ?.commit()

            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                val serverResponseData = data.serverResponseData

                val url = serverResponseData.url

                if (url.isNullOrEmpty()) {
                    toast("Link is empty")
                } else {
                    binding.imageView.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_load_error_vector) //если картинка не загрузится
                        placeholder(R.drawable.ic_no_photo_vector) //картинка во время загрузки основной
                        crossfade(true)
                    }
                    renderBottomSheet(serverResponseData)
                }
            }

            is AppState.Loading -> {
                //TODO
            }

            is AppState.Error -> {
                toast(data.error.message)
            }
        }
    }

    private fun renderBottomSheet(serverResponseData: PictureOfDayDTO) {
        (view?.findViewById(R.id.bottomSheetDescriptionHeader) as TextView).also {
            it.text = "${serverResponseData.title}"
        }
        (view?.findViewById(R.id.bottomSheetDescription) as TextView).also {
            with(serverResponseData)
            { it.text = "${explanation}\n\n\n ${date}" }
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
        setHasOptionsMenu(true)

        binding.fab.setOnClickListener {
            if (isMain) {
                isMain = false
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(menu.menu_bottom_bar_other_screen)
            } else {
                isMain = true
                binding.bottomAppBar.navigationIcon =
                    ContextCompat.getDrawable(context, drawable.ic_hamburger_menu_bottom_bar)
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(menu.menu_bottom_bar)
            }
        }
    }

    private fun enlargePictureFirst() {
        binding.imageView.apply {
            scaleType = ImageView.ScaleType.CENTER
        }
    }

    private fun animatePhotoClick() {
        binding.imageView.setOnClickListener {
            isZoomed = !isZoomed
            TransitionManager.beginDelayedTransition(
                binding.root,
                TransitionSet()
                    .addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform())
            )


            binding.imageView.apply {
                scaleType =
                    if (isZoomed) ImageView.ScaleType.CENTER_INSIDE else ImageView.ScaleType.CENTER_CROP
            }
        }
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
        private var isMain = true
    }
}