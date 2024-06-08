package com.example.storyapp.appearance.fragment.setting

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.storyapp.R
import com.example.storyapp.base.BaseFragment
import com.example.storyapp.databinding.FragmentSettingBinding
import com.example.storyapp.utils.helper.showChooserDialog
import org.koin.android.ext.android.inject

class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override fun assignBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(inflater, container, false)
    }

    private val viewModel: SettingViewModel by inject()

    override fun doSomething() {
        super.doSomething()

        initListener()
    }

    private fun initListener() {
        binding.actionLogout.setOnClickListener {
            showChooserDialog(
                title = getString(R.string.logout),
                message = getString(R.string.logout_message),
                positiveButtonText = getString(R.string.yes),
                negativeButtonText = getString(R.string.no),
                onPositiveClick = {
                    logOut()
                }
            )
        }

        binding.changeLanguageButton.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
    }

    private fun logOut() {
        viewModel.logout().apply {
            if (this) {
                val direction =
                    SettingFragmentDirections.actionSettingsFragmentToAuthFragment()
                findNavController().navigate(direction)
            }
        }
    }
}