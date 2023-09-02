package com.elthobhy.newsapp.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.elthobhy.core.utils.dialogError
import com.elthobhy.core.utils.dialogLoading
import com.elthobhy.core.utils.vo.Status
import com.elthobhy.newsapp.databinding.ActivityRegisterBinding
import com.elthobhy.newsapp.ui.auth.login.LoginActivity
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel by inject<RegisterViewModel>()
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog= dialogLoading(this)
        onClick()
    }

    private fun onClick() {
        binding.apply {
            btnRegister.setOnClickListener {
                val name = editTextName.text.toString().trim()
                val email = editTextEmail.text.toString().trim()
                val pass = editTextPassword.text.toString().trim()
                val confirmPass = editTextConfirmPassword.text.toString().trim()

                if (checkValidation(email, pass, confirmPass)) {
                    registerViewModel.register(name, email, pass).observe(this@RegisterActivity) {
                        when (it.status) {
                            Status.LOADING -> {
                                dialog.show()
                                binding.pbRegister.visibility = View.VISIBLE
                            }
                            Status.SUCCESS -> {
                                dialog.dismiss()
                                binding.pbRegister.visibility = View.GONE
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "User Created",
                                    Toast.LENGTH_LONG
                                ).show()
                                startActivity(
                                    Intent(
                                        this@RegisterActivity,
                                        LoginActivity::class.java
                                    )
                                )
                                finishAffinity()
                            }
                            Status.ERROR -> {
                                dialog.dismiss()
                                dialogError(it.message,this@RegisterActivity).show()
                                binding.pbRegister.visibility = View.GONE
                                Toast.makeText(
                                    this@RegisterActivity,
                                    it.message,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                }
            }
            btnCloseRegister.setOnClickListener {
                finish()
            }
        }
    }

    private fun checkValidation(email: String, pass: String, confirmPass: String): Boolean {
        binding.apply {
            when {
                email.isEmpty() -> {
                    editTextEmail.error = "Please Field Your Email"
                    editTextEmail.requestFocus()
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    editTextEmail.error = "Please Use Valid Email"
                    editTextEmail.requestFocus()
                }
                pass.isEmpty() -> {
                    editTextPassword.error = "Please Field Your Password"
                    editTextPassword.requestFocus()
                }
                confirmPass.isEmpty() -> {
                    editTextConfirmPassword.error = "Please Field Your Confirm Password"
                    editTextConfirmPassword.requestFocus()
                }
                pass != confirmPass -> {
                    editTextPassword.error = "Your confirm password didn't match with password"
                    editTextConfirmPassword.error =
                        "Your confirm password didn't match with password"
                    editTextPassword.requestFocus()
                    editTextConfirmPassword.requestFocus()
                }
                else -> {
                    return true
                }
            }
        }
        return false
    }
}