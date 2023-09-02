package com.elthobhy.newsapp.ui.auth.changepassword

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.elthobhy.core.utils.dialogError
import com.elthobhy.core.utils.dialogLoading
import com.elthobhy.core.utils.vo.Status
import com.elthobhy.newsapp.databinding.ActivityChangePasswordBinding
import com.elthobhy.newsapp.ui.account.UserActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import org.koin.android.ext.android.inject

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding
    private var currentUser: FirebaseUser? = null
    private val changePasswordViewModel by inject<ChangePasswordViewModel>()
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentUser = FirebaseAuth.getInstance().currentUser
        dialog= dialogLoading(this)
        onClick()
    }

    private fun onClick() {
        binding.apply {
            btnChangePassword.setOnClickListener {
                val oldPass: String = etOldPassword.text.toString().trim()
                val newPass = etNewPassword.text.toString().trim()
                val confirmNewPass = etConfirmNewPassword.text.toString().trim()
                if(validationCheck(newPass, confirmNewPass)){
                    val user = GoogleSignIn.getLastSignedInAccount(this@ChangePasswordActivity)
                    if(oldPass.isEmpty() && user?.idToken?.isNotEmpty() == true){
                        val credential = GoogleAuthProvider.getCredential(user.idToken, null)
                        changePass(newPass, credential)
                    }else{
                        val credential = EmailAuthProvider.getCredential(currentUser?.email.toString(), oldPass)
                        changePass(newPass, credential)
                    }
                }
            }
            btnCloseChangePassword.setOnClickListener {
                finish()
            }
        }
    }

    private fun changePass(newPass: String, credential: AuthCredential) {
            changePasswordViewModel.changePassword(newPass, credential).observe(this){
                when (it.status) {
                    Status.LOADING -> { dialog.show() }
                    Status.SUCCESS -> {
                        dialog.dismiss()
                        Toast.makeText(
                            this@ChangePasswordActivity,
                            "Success",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(
                            Intent(
                                this@ChangePasswordActivity,
                                UserActivity::class.java
                            )
                        )
                        finishAffinity()
                    }
                    Status.ERROR -> {
                        dialog.dismiss()
                        dialogError(it.message,this).show()
                        Toast.makeText(
                            this@ChangePasswordActivity,
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()
                        Log.e("error guys", "changePass: ${it.message}" )
                    }
                }
        }
    }

    private fun validationCheck(newPass: String, confirmNewPass: String): Boolean {
        binding.apply {
            when{
                newPass.isEmpty()->{
                    etNewPassword.error = "Please Field Your New Password"
                    etNewPassword.requestFocus()
                }
                confirmNewPass.isEmpty()->{
                    etConfirmNewPassword.error = "Please Field Confirm New Password"
                    etConfirmNewPassword.requestFocus()
                }
                newPass.length < 8 ->{
                    etNewPassword.error = "Minimum password is 8 character"
                    etNewPassword.requestFocus()
                }
                confirmNewPass.length < 8 ->{
                    etConfirmNewPassword.error = "Minimum password is 8 character"
                    etConfirmNewPassword.requestFocus()
                }
                newPass != confirmNewPass ->{
                    etNewPassword.error = "confirm password didn't match"
                    etNewPassword.requestFocus()
                    etConfirmNewPassword.error = "confirm password didn't match"
                    etConfirmNewPassword.requestFocus()
                }
                else->{
                    return true
                }
            }
        }
        return false
    }
}