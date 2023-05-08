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

class InsSignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ins_signup)

        val txtInstituteName: TextInputEditText = findViewById(R.id.txtInstituteName)
        val txtInstituteAddress: TextInputEditText = findViewById(R.id.txtInstituteAddress)
        val txtInstituteDescription:TextInputEditText = findViewById(R.id.txtInstituteDescription)
        val txtInstituteUserName:TextInputEditText = findViewById(R.id.txtInstituteUserName)
        val txtInstitutePassword:TextInputEditText = findViewById(R.id.txtInstitutePassword)
        val btnInstituteRegister:Button = findViewById(R.id.btnInstitutesRegister)

        btnInstituteRegister.setOnClickListener {
            val name:String = txtInstituteName.text.toString()
            val address:String = txtInstituteAddress.text.toString()
            val description:String = txtInstituteDescription.text.toString()
            val username:String = txtInstituteUserName.text.toString()
            val password:String = txtInstitutePassword.text.toString()

            if (name.equals("") && address.equals("") && description.equals("") && username.equals("") && password.equals("")){
                Toast.makeText(applicationContext,"Values Empty",Toast.LENGTH_LONG).show()
            }else{
                postRequest(name,address,description,username,password)
            }
        }
    }

    private fun postRequest(name: String, address: String, description: String, username: String, password: String) {
        val url:String="http://192.168.8.102/senan/api/addinstitute.php"
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        val stringRequest: StringRequest = object: StringRequest(Request.Method.POST,url, Response.Listener { response ->
            Toast.makeText(applicationContext,response.trim().toString(),Toast.LENGTH_LONG).show()
            if (response.trim().equals("Institute Registerd Successfully")){
                var intent: Intent = Intent(this,DashboardActivity::class.java)
                startActivity(intent)
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(applicationContext,error.message,Toast.LENGTH_LONG).show()
        }){
            override fun getParams(): MutableMap<String, String>? {
                val params=HashMap<String,String>()
                params.put("name",name)
                params.put("address",address)
                params.put("description",description)
                params.put("username",username)
                params.put("password",password)

                return params
            }
        }
        requestQueue.add(stringRequest)
    }
}