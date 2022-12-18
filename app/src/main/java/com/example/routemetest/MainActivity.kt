package com.example.routemetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.routemetest.activities.RegisterActivity
import com.example.routemetest.databinding.ActivityMainBinding
import com.example.routemetest.models.User
import com.example.routemetest.ui.fragments.OrdersFragment
import com.example.routemetest.ui.objects.AppDrawer
import com.example.routemetest.utilities.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY = this
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
        initFirebase()
        initUser()

    }

    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener{
                USER = it.getValue(User::class.java) ?:User()
            })
    }
}