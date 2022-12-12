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
class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {
 private lateinit var mBinding: FragmentChangeNameBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initFullnameList(inflater, container)

        return mBinding.root
    }

    private fun initFullnameList(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        mBinding = FragmentChangeNameBinding.inflate(inflater, container, false)
        val fullnameList = USER.fullname.split(" ")
        if (fullnameList.size > 1) {
            mBinding.settingsInputName.setText(fullnameList[0])
            mBinding.settingsInputSurname.setText(fullnameList[1])
        } else {
            mBinding.settingsInputName.setText(fullnameList[0])
        }
    }


    override fun change() {

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