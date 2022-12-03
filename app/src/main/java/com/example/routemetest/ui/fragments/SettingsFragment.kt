package com.example.routemetest.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.routemetest.MainActivity
import com.example.routemetest.R
import com.example.routemetest.activities.RegisterActivity
import com.example.routemetest.databinding.ActivityRegisterBinding
import com.example.routemetest.utilities.AUTH
import com.example.routemetest.utilities.replaceActivity
import com.example.routemetest.utilities.replaceFragment


class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_menu_exit ->{
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
            R.id.settings_menu_change_name -> replaceFragment(ChangeNameFragment())
        }
        return true
    }
}