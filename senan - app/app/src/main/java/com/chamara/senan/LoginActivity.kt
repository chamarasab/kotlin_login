package com.chamara.senan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txtUsername:TextInputEditText=findViewById(R.id.txtUserName)
        val txtPassword:TextInputEditText=findViewById(R.id.txtUserPassword)
        val btnLogin:Button=findViewById(R.id.btnUserLogin)
        val btnSignUp:Button=findViewById(R.id.btnUserSignUp)
        btnSignUp.setOnClickListener {
            val intent:Intent= Intent(applicationContext,SignUpActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            val username:String=txtUsername.text.toString()
            val password:String=txtPassword.text.toString()
            loginRequest(username,password)
        }
    }
    private fun loginRequest(username: String, password: String) {
        //val url:String="http://192.168.8.102/senan/api/api_login.php"
        val url:String="https://sibauniversity.000webhostapp.com/api_login.php"
        val requestQueue:RequestQueue = Volley.newRequestQueue(this)
        val stringRequest:StringRequest= object: StringRequest(Request.Method.POST,url, Response.Listener { response ->
            if (response.trim().toString().equals("Welcome")){
                val intent:Intent= Intent(applicationContext,DashboardActivity::class.java)
                startActivity(intent)
            }else{
                //clear text-boxes
            }
            Toast.makeText(applicationContext,response.toString(),Toast.LENGTH_LONG).show()
        }, Response.ErrorListener { error ->

        }){
            override fun getParams(): MutableMap<String, String>? {
                val params=HashMap<String,String>()
                params.put("username",username)
                params.put("password",password)
                return params
            }
        }
        requestQueue.add(stringRequest)
    }
}