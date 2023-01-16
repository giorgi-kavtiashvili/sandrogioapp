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
import com.example.lumaspotify.databinding.FragmentForgotpasswordBinding
import com.google.firebase.auth.FirebaseAuth


class ForgotPasswordFragment : Fragment(R.layout.fragment_forgotpassword) {
    private lateinit var binding: FragmentForgotpasswordBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentForgotpasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ForgotPasswordActivityListeners()
        onClickListener()
    }

    private fun ForgotPasswordActivityListeners(){
        binding.sendMailBt.setOnClickListener {
            val email = binding.email.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "გთხოვთ შეიყვანეთ თქვენი მეილი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "შეამოწმეთ მეილი", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireActivity(), LoginFragment::class.java))
                } else {
                    Toast.makeText(requireContext(), "დაფიქსირდა შეცდომა", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}
fun onClickListener() {
    binding.btBacklog.setOnClickListener {
        findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment())
    }
}

