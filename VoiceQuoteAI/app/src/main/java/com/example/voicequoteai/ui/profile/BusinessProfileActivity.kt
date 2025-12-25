package com.example.voicequoteai.ui.profile

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.voicequoteai.data.model.BusinessProfile
import com.example.voicequoteai.databinding.ActivityBusinessProfileBinding
import com.example.voicequoteai.di.ViewModelFactory

class BusinessProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBusinessProfileBinding
    private val viewModel: ProfileViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.profile.observe(this) { profile ->
            profile?.let {
                binding.etBusinessName.setText(it.businessName)
                binding.etOwnerName.setText(it.ownerName)
                binding.etPhone.setText(it.phone)
                binding.etEmail.setText(it.email)
                binding.etGst.setText(it.gstNumber)
            }
        }

        viewModel.loadProfile()

        binding.btnSave.setOnClickListener {
            saveProfile()
        }
    }

    private fun saveProfile() {
        val profile = BusinessProfile(
            businessName = binding.etBusinessName.text.toString(),
            ownerName = binding.etOwnerName.text.toString(),
            phone = binding.etPhone.text.toString(),
            email = binding.etEmail.text.toString(),
            gstNumber = binding.etGst.text.toString()
        )

        viewModel.saveProfile(profile)
        finish()
    }
}
