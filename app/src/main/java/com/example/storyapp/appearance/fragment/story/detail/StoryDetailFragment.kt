package com.example.storyapp.appearance.fragment.story.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.storyapp.base.BaseFragment
import com.example.storyapp.data.model.status.story.Story
import com.example.storyapp.databinding.FragmentStoryDetailBinding


class StoryDetailFragment : BaseFragment<FragmentStoryDetailBinding>() {


    override fun assignBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentStoryDetailBinding {
        return FragmentStoryDetailBinding.inflate(inflater, container, false)

    }

    var item: Story? = null

    override fun doSomething() {
        super.doSomething()
        item =
            StoryDetailFragmentArgs.fromBundle(requireArguments()).story

        with(binding) {
            ivDetailPhoto.load(item?.photoUrl)
            tvDetailName.text = item?.name
            tvDetailDescription.text = item?.description
            topBar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}