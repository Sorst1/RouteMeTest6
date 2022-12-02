package com.example.routemetest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.routemetest.MainActivity
import com.example.routemetest.R
import com.example.routemetest.activities.RegisterActivity
import com.example.routemetest.databinding.FragmentEnterPhoneNumberBinding
import com.example.routemetest.utilities.AUTH
import com.example.routemetest.utilities.replaceActivity
import com.example.routemetest.utilities.replaceFragment
import com.example.routemetest.utilities.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


@Suppress("DEPRECATION")
class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {
private lateinit var mBinding: FragmentEnterPhoneNumberBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding= FragmentEnterPhoneNumberBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    private lateinit var mPhoneNumber:String
    private lateinit var mCallback:PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onStart() {
        super.onStart()
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
               AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        showToast("Добро пожаловать")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else showToast(task.exception?.message.toString())
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCodeFragment(mPhoneNumber, id))
            }
        }
        mBinding.registerBtnNext.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (mBinding.registerInputPhoneNumber.text.toString().isEmpty()){
            showToast(getString(R.string.register_toast_enter_phone))
        } else {
            authUser()

        }
    }

    private fun authUser() {
        mPhoneNumber =mBinding.registerInputPhoneNumber.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber,
            60,
            TimeUnit.SECONDS,
            activity as RegisterActivity,
            mCallback

        )
    }
}