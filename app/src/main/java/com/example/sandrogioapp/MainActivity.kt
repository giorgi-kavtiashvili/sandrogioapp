package com.example.sandrogioapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lumaspotify.R
import com.example.lumaspotify.databinding.ActivityMainBinding
import com.example.sandrogioapp.fragments.HomeFragment
import com.example.sandrogioapp.fragments.ImageUploadFragment
import com.example.sandrogioapp.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemReselectedListener {
            when(it.itemId){
                R.id.homeFragment -> replaceFragment(HomeFragment())
                R.id.profileFragment -> replaceFragment(ProfileFragment())
                R.id.imageFragment -> replaceFragment(ImageUploadFragment())

                else ->{

                }
            }

            true

        }


    }
    private fun replaceFragment(fragment : Fragment){


        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

}