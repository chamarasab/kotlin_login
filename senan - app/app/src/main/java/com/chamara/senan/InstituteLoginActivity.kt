package com.chamara.senan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText

class InstituteLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_institute_login)

        val txtUsername: TextInputEditText =findViewById(R.id.txtInsName)
        val txtPassword: TextInputEditText =findViewById(R.id.txtInsPassword)
        val btnLogin: Button =findViewById(R.id.btnInsLogin)
        val btnSignUp: Button =findViewById(R.id.btnInsSignUp)

        btnSignUp.setOnClickListener {
            val intent: Intent = Intent(applicationContext,InsSignupActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val username : String = txtUsername.text.toString()
            val password : String = txtPassword.text.toString()
            loginPostRequest(username,password)
        }
    }

    private fun loginPostRequest(username: String, password: String) {
        //val url:String="http://192.168.8.102/senan/api/api_institutelogin.php"
        val url:String="https://sibauniversity.000webhostapp.com/api_institutelogin.php"
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        val stringRequest: StringRequest = object: StringRequest(Request.Method.POST,url, Response.Listener { response ->
            if (response.trim().toString().equals("Welcome")){
                val intent:Intent= Intent(applicationContext,InstituteDashboardActivity::class.java)
                startActivity(intent)
            }else{
                //clear text-boxes
            }
            Toast.makeText(applicationContext,response.toString(), Toast.LENGTH_LONG).show()
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