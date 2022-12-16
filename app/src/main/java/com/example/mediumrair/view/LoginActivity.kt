package com.example.mediumrair.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mediumrair.API.ApiInterface
import com.example.mediumrair.API.RetrofitInstance
import com.example.mediumrair.databinding.ActivityLoginBinding
import com.example.mediumrair.model.Login
import com.example.mediumrair.viewmodel.LoginViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var viewBind: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        loginViewModel = ViewModelProvider(this) [LoginViewModel::class.java]
        listener()
    }

    private fun listener() {
        Log.d("Listener", "hai")
        val email: String = viewBind.loginInputLayoutEmail.editText?.text.toString()
        val password: String = viewBind.loginInputLayoutPassword.editText?.text.toString()

        viewBind.loginBtnLogin.setOnClickListener {
            login(email, password)
        }
        viewBind.loginBtnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(email: String, password: String){
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val signInInfo = Login(email, password)
        retIn.login(signInInfo).enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    this@LoginActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Toast.makeText(this@LoginActivity, response.code(), Toast.LENGTH_SHORT).show()
                if (response.code() == 200) {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}