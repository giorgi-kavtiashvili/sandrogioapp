package com.example.sandrogioapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lumaspotify.R
import com.example.lumaspotify.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth


class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    private lateinit var binding: FragmentRegistrationBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListener()
        RegisterActivityListeners()
    }

    private fun RegisterActivityListeners(){
        binding.btReg.setOnClickListener {
            val email=binding.email.text.toString()
            val pass=binding.pass.text.toString()

            if (email.isEmpty() || pass.isEmpty()){
                Toast.makeText(requireContext(), "გთხოვთ შეავსეთ ველებია", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(requireActivity(), HomeFragment::class.java))
                } else {
                    Toast.makeText(requireContext(), "დაფიქსირდა შეცდომა", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun onClickListener(){
        binding.btReg.setOnClickListener{
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment())
        }
        binding.logBut.setOnClickListener {
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
        }
    }


}