package com.example.storyapp.appearance.fragment.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.storyapp.R
import com.example.storyapp.base.BaseFragment
import com.example.storyapp.data.model.status.auth.RegisterRequest
import com.example.storyapp.data.model.status.network.ApiStatus
import com.example.storyapp.databinding.FragmentRegisterBinding
import com.example.storyapp.utils.showToast
import org.koin.android.ext.android.inject

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override fun assignBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(inflater, container, false)
    }

    private val viewModel: RegisterViewModel by inject()

    override fun doSomething() {
        super.doSomething()

        initListener()
        initObserver()
        playAnimation()
    }

    private fun initListener() {
        binding.signupButton.setOnClickListener {
            val name = binding.edRegisterName.text.toString()
            val email = binding.edRegisterEmail.text?.trim().toString()
            val password = binding.edRegisterPassword.text?.trim().toString()

            val request = RegisterRequest(
                name,
                email,
                password,
            )

            if (email.isEmpty()) {
                binding.edRegisterEmail.error =
                    getString(R.string.error_email_empty)
            } else if (password.isEmpty()) {
                binding.edRegisterPassword.error =
                    getString(R.string.error_password_empty)
            } else {
                viewModel.register(request)
            }
        }
    }

    private fun initObserver() {
        viewModel.registerResult.observe(this) {
            when (it) {
                is ApiStatus.Loading -> {
                    binding.loadingButton.root.visibility = View.VISIBLE
                }

                is ApiStatus.Success -> {
                    showToast(requireActivity(), it.data.message)
                    val direction =
                        RegisterFragmentDirections.actionRegisterFragmentToAuthFragment()
                    findNavController().navigate(direction)
                }

                is ApiStatus.Error -> {
                    showToast(requireActivity(), it.errorMessage)
                    binding.loadingButton.root.visibility = View.GONE
                }

                else -> {
                    binding.loadingButton.root.visibility = View.GONE
                }
            }
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(
            binding.imageView, View.TRANSLATION_X, -30f,
            30f
        ).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }

        val title =
            ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f)
                .setDuration(100)
        val namaText =
            ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f)
                .setDuration(100)
        val nama =
            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f)
                .setDuration(100)
        val emailText =
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f)
                .setDuration(100)
        val email =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f)
                .setDuration(100)
        val passwordText =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f)
                .setDuration(100)
        val password = ObjectAnimator.ofFloat(
            binding.passwordEditTextLayout,
            View.ALPHA,
            1f
        ).setDuration(100)
        val signUpButton =
            ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f)
                .setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                title,
                namaText,
                nama,
                emailText,
                email,
                passwordText,
                password,
                signUpButton
            )
        }.start()
    }
}