package com.example.sandrogioapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.lumaspotify.R
import com.example.lumaspotify.databinding.FragmentRegistrationBinding
import com.example.lumaspotify.databinding.FragmentResetpasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordFragment :  Fragment(R.layout.fragment_resetpassword) {
    private lateinit var binding: FragmentResetpasswordBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentResetpasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListener()
        ResetPasswordActivityListeners()
    }

    private fun ResetPasswordActivityListeners() {
        binding.resetBt.setOnClickListener {
            val currentPass = binding.currentPass.text.toString()
            val newPass1 = binding.newPass1.text.toString()
            val newPass2 = binding.newPass2.text.toString()

            if (currentPass.isEmpty() || newPass1.isEmpty() || newPass2.isEmpty()) {
                Toast.makeText(requireContext(), "გთხოვთ შეავსეთ ველები", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if (newPass1 != newPass2) {
                Toast.makeText(
                    requireContext(),
                    "პაროლები არ ემთხვევა ერთმანეთს",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            if (newPass1 == newPass2) {
                FirebaseAuth.getInstance().currentUser?.updatePassword(newPass1)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(requireActivity(), LoginFragment::class.java))
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "მონაცემები არასწორია",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(requireContext(), "მოხდა შეცდომა", Toast.LENGTH_LONG).show()
            }
        }
    }


    fun onClickListener() {
        binding.backToPrBt.setOnClickListener {
            findNavController().navigate(ResetPasswordFragmentDirections.actionResetPasswordFragmentToLoginFragment())
        }
    }
}