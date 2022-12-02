package com.example.routemetest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.routemetest.R
import com.example.routemetest.databinding.ActivityMainBinding
import com.example.routemetest.databinding.ActivityRegisterBinding
import com.example.routemetest.ui.fragments.EnterPhoneNumberFragment
import com.example.routemetest.utilities.replaceActivity
import com.example.routemetest.utilities.replaceFragment


class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        mToolbar = mBinding.registerToolbar
        setSupportActionBar(mToolbar)
        title = getString(R.string.register_title_your_phone)
        replaceFragment(EnterPhoneNumberFragment())
    }
}