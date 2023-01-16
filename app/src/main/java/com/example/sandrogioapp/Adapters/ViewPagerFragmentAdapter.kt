package com.example.sandrogioapp.Adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sandrogioapp.fragments.HomeFragment
import com.example.sandrogioapp.fragments.ImageUploadFragment
import com.example.sandrogioapp.fragments.ProfileFragment

class ViewPagerFragmentAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return HomeFragment()
        } else if (position == 1) {
            return ImageUploadFragment()
        } else {
            return ProfileFragment()
        }

    }
}