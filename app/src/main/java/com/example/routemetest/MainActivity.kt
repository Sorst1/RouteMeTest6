package com.example.routemetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.routemetest.activities.RegisterActivity
import com.example.routemetest.databinding.ActivityMainBinding
import com.example.routemetest.ui.fragments.EnterPhoneNumberFragment
import com.example.routemetest.ui.fragments.OrdersFragment
import com.example.routemetest.ui.objects.AppDrawer
import com.example.routemetest.utilities.AUTH
import com.example.routemetest.utilities.replaceActivity
import com.example.routemetest.utilities.replaceFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        if (true) { //AUTH.currentUser!=null
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(OrdersFragment())
        } else{
            replaceActivity(RegisterActivity())

        }
    }


    private fun initFields(){
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
        AUTH = FirebaseAuth.getInstance()


    }
}