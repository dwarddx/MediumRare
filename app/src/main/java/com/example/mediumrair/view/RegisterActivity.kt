package com.example.mediumrair.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mediumrair.API.ApiInterface
import com.example.mediumrair.API.RetrofitInstance
import com.example.mediumrair.databinding.ActivityRegisterBinding
import com.example.mediumrair.model.User
import com.example.mediumrair.viewmodel.RegisterViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var viewBind: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        registerViewModel = ViewModelProvider(this) [RegisterViewModel::class.java]
        listener()
    }

    private fun listener() {
        val name: String = viewBind.registerInputTextName.toString()
        val email: String = viewBind.registerInputTextEmail.toString()
        val password: String = viewBind.registerInputTextPassword.toString()
        var repeat: String = viewBind.registerInputTextRepeat.toString()

            viewBind.registerBtnRegister.setOnClickListener {
                signup(name, email, password)
            }
        viewBind.registerBtnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signup(Name: String, Email: String, Password: String){
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val registerInfo = User(Name, Email, Password)

        retIn.registerUser(registerInfo).enqueue(object :
            Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    this@RegisterActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
                    val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this@RegisterActivity, "Registration failed!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}