package com.example.routemetest.ui.fragments

import android.os.Bundle
import android.view.*
import com.example.routemetest.MainActivity
import com.example.routemetest.R
import com.example.routemetest.activities.RegisterActivity
import com.example.routemetest.databinding.ActivityRegisterBinding
import com.example.routemetest.databinding.FragmentSettingsBinding
import com.example.routemetest.utilities.AUTH
import com.example.routemetest.utilities.USER
import com.example.routemetest.utilities.replaceActivity
import com.example.routemetest.utilities.replaceFragment


class SettingsFragment : BaseFragment(R.layout.fragment_settings) {
    private lateinit var mBinding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        mBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        initFields()
        return mBinding.root
    }

 private fun initFields(){
    mBinding.settingsFullName.text = USER.fullname
    mBinding.settingsUsername.text = USER.username
    mBinding.settingsPhoneNumber.text = USER.phone
     mBinding.settingsStatus.text = USER.status
     mBinding.settingsBtnChangeUsername.setOnClickListener { replaceFragment(ChangeUsernameFragment()) }
     mBinding.settingsBtnChangeStatus.setOnClickListener { replaceFragment(ChangeStatusFragment()) }
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