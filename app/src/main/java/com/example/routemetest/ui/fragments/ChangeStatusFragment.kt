package com.example.routemetest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.routemetest.R
import com.example.routemetest.databinding.FragmentChangeStatusBinding
import com.example.routemetest.databinding.FragmentChangeUsernameBinding
import com.example.routemetest.utilities.*


@Suppress("DEPRECATION")
class ChangeStatusFragment : BaseChangeFragment(R.layout.fragment_change_status) {

private lateinit var mBinding: FragmentChangeStatusBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentChangeStatusBinding.inflate(inflater, container, false)
        mBinding.settingsInputStatus.setText(USER.status)
        return mBinding.root
    }

    override fun change() {
        super.change()
        val newStatus = mBinding.settingsInputStatus.text.toString()
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_STATUS).setValue(newStatus)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    showToast(getString(R.string.toast_data_update))
                    USER.status = newStatus
                    fragmentManager?.popBackStack()
                }
            }
    }


}