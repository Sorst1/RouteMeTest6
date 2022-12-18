package com.example.routemetest.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import com.example.routemetest.R
import com.example.routemetest.activities.RegisterActivity
import com.example.routemetest.databinding.FragmentSettingsBinding
import com.example.routemetest.utilities.*


@Suppress("DEPRECATION")
class SettingsFragment : BaseFragment(R.layout.fragment_settings) {
    private lateinit var mBinding: FragmentSettingsBinding

    private val GALLERY_REQUEST_CODE = 1234

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
     mBinding.settingsChangePhoto.setOnClickListener{changePhotoUser()}
 }

    private fun changePhotoUser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        val mimeTypes = arrayOf("image/jpeg", "image/png", "image/jpg")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        startActivityForResult(intent, GALLERY_REQUEST_CODE)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_menu_exit ->{
                AUTH.signOut()
                (APP_ACTIVITY).replaceActivity(RegisterActivity())
            }
            R.id.settings_menu_change_name -> replaceFragment(ChangeNameFragment())
        }
        return true
    }






}