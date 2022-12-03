package com.example.routemetest.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.routemetest.MainActivity
import com.example.routemetest.R
import com.example.routemetest.databinding.ActivityMainBinding
import com.example.routemetest.databinding.ActivityRegisterBinding
import com.example.routemetest.databinding.FragmentChangeNameBinding
import com.example.routemetest.databinding.FragmentEnterCodeBinding
import com.example.routemetest.utilities.*

@Suppress("DEPRECATION")
class ChangeNameFragment : Fragment(R.layout.fragment_change_name) {
 private lateinit var mBinding: FragmentChangeNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_confirm_change -> changeName()

        }
        return true
    }

    private fun changeName() {

        val name = mBinding.settingsInputName.text.toString()
        val surname = mBinding.settingsInputSurname.text.toString()
        if (name.isEmpty()){
           showToast(getString(R.string.settings_toast_name_is_empty))
        } else {
            val fullname = "$name $surname".trim()
            REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_FULLNAME)
                .setValue(fullname).addOnCompleteListener {
                    if(it.isSuccessful){
                        showToast(getString(R.string.toast_data_update))
                        USER.fullname=fullname
                        fragmentManager?.popBackStack()

                    }
                }
        }
    }
}