package com.me.harris.textviewtest.edittextpassword

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.me.harris.textviewtest.R
import kotlinx.android.synthetic.main.activity_edit_text.*
import android.text.InputType



class EditTextActivity:AppCompatActivity() {

    var showPassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)
        btn_toggle.setOnClickListener {
//            if (!showPassword){
//                et_password.transformationMethod = PasswordTransformationMethod.getInstance()
//            }else{
//                et_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
//            }
            if (showPassword){
                et_password.inputType = InputType.TYPE_CLASS_TEXT
            }else{
                et_password.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT

            }
            et_password.setSelection(et_password.text.toString().length)
            showPassword = !showPassword
        }
    }
}