package com.example.routemetest.utilities

import android.text.Editable
import android.text.TextWatcher

class AppTextWatcher (val onSuccess: (Editable?) -> Unit):TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {

        onSuccess(s)
    }
}