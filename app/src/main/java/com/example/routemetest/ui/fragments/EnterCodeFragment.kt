package com.example.routemetest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.routemetest.MainActivity
import com.example.routemetest.R
import com.example.routemetest.activities.RegisterActivity
import com.example.routemetest.databinding.FragmentEnterCodeBinding
import com.example.routemetest.utilities.*
import com.google.firebase.auth.PhoneAuthProvider


class EnterCodeFragment(val phoneNumber: String, val id: String) : Fragment(R.layout.fragment_enter_code) {
    private lateinit var mBinding: FragmentEnterCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentEnterCodeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNumber
        mBinding.registerInputCode.addTextChangedListener(AppTextWatcher {
            val string = mBinding.registerInputCode.text.toString()
            if (string.length >= 6) {
                enterCode()
            }
        })
    }

    private fun enterCode() {
        val code = mBinding.registerInputCode.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = phoneNumber
                dateMap[CHILD_USERNAME] = uid

                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                    .addOnCompleteListener { task2 ->
                        if (task2.isSuccessful){
                            showToast("Добро пожаловать")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                        }  else showToast(task2.exception?.message.toString())
                    }
            } else showToast(task.exception?.message.toString())
        }
    }
}