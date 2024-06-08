package com.example.storyapp.appearance.fragment.story.add

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.storyapp.R
import com.example.storyapp.base.BaseFragment
import com.example.storyapp.data.model.status.network.ApiStatus
import com.example.storyapp.databinding.FragmentStoryAddBinding
import com.example.storyapp.utils.getImageUri
import com.example.storyapp.utils.showToast
import com.yalantis.ucrop.UCrop
import org.koin.android.ext.android.inject
import java.io.File
import java.util.Date

class StoryAddFragment : BaseFragment<FragmentStoryAddBinding>() {
    override fun assignBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentStoryAddBinding {
        return FragmentStoryAddBinding.inflate(inflater, container, false)
    }

    private val viewModel: StoryAddViewModel by inject()

    private var currentUri: Uri? = null
    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentUri = uri
            withUcrop(currentUri!!)
        }
    }
    private val launcherUCrop =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val resultUri = UCrop.getOutput(result.data!!)
                if (resultUri != null) {
                    currentUri = resultUri
                    setPreview()
                }
            }
        }

    private val launcherCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            withUcrop(currentUri!!)
        }
    }

    private fun withUcrop(uri: Uri) {
        val timestamp = Date().time
        val cachedImage =
            File(requireActivity().cacheDir, "cropped_image_${timestamp}.jpg")

        val destinationUri = Uri.fromFile(cachedImage)

        val uCrop = UCrop.of(uri, destinationUri).withAspectRatio(1f, 1f)

        uCrop.getIntent(requireContext()).apply {
            launcherUCrop.launch(this)
        }
    }

    private fun setPreview() {
        binding.ivPreviewStory.setImageURI(currentUri)
    }

    override fun doSomething() {
        super.doSomething()

        initListener()
        initObserver()
    }

    private fun initListener() {

        with(binding) {
            topBar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            cameraButton.setOnClickListener {
                currentUri = getImageUri(requireActivity())
                launcherCamera.launch(currentUri)
            }
            galleryButton.setOnClickListener {
                launcherGallery.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }
            buttonAdd.setOnClickListener {
                val description = edAddDescription.text.toString()

                if (description.isNotEmpty() && currentUri != null) {
                    viewModel.uploadStory(
                        currentUri!!,
                        description,
                    )
                } else {
                    showToast(
                        requireActivity(),
                        getString(R.string.error_upload_story)
                    )
                }
            }
        }

    }

    private fun initObserver() {
        viewModel.uploadStoryResult.observe(viewLifecycleOwner) { result ->
            binding.apply {
                when (result) {
                    is ApiStatus.Loading -> loadingButton.root.visibility =
                        View.VISIBLE

                    is ApiStatus.Success -> {
                        loadingButton.root.visibility = View.GONE
                        showToast(
                            requireActivity(),
                            result.data.message
                        )
                        findNavController().popBackStack()
                    }

                    is ApiStatus.Error -> {
                        loadingButton.root.visibility = View.GONE
                        showToast(
                            requireActivity(),
                            result.errorMessage
                        )
                    }

                    else -> loadingButton.root.visibility = View.GONE
                }
            }
        }
    }
}