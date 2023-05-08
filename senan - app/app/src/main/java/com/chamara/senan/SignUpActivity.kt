package com.chamara.senan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val txtName:TextInputEditText=findViewById(R.id.txtName)
        val txtAddress:TextInputEditText=findViewById(R.id.txtAddress)
        val txtSubject:TextInputEditText=findViewById(R.id.txtSubject)
        val txtGrade:TextInputEditText=findViewById(R.id.txtGrade)
        val txtUsername:TextInputEditText=findViewById(R.id.txtInsName)
        val txtPassword:TextInputEditText=findViewById(R.id.txtInsPassword)
        var txtImage:String="avatar1.jpeg"
        val btnRegister:Button=findViewById(R.id.btnRegister)
        val cboUserType:AutoCompleteTextView=findViewById(R.id.cboUserType)
        var usertypes =  arrayOf<kotlin.String>("Instructor","Student")
        var arrayAddpter=ArrayAdapter(this,R.layout.user_types,usertypes)
        cboUserType.setAdapter(arrayAddpter)

        btnRegister.setOnClickListener {
            var name:String=txtName.text.toString()
            var address:String=txtAddress.text.toString()
            var subject:String=txtSubject.text.toString()
            var grade:String=txtGrade.text.toString()
            var username:String=txtUsername.text.toString()
            var password:String=txtPassword.text.toString()
            var image:String=txtImage.toString()
            var usertype:String=cboUserType.text.toString()

            if (name.equals("")&&address.equals("")&&subject.equals("")&&grade.equals("")&&username.equals("")&&password.equals("")){
                Toast.makeText(applicationContext,"Fill the fields",Toast.LENGTH_LONG).show()
            }else{
                //Toast.makeText(applicationContext,"Passed to postRequest()",Toast.LENGTH_LONG).show()
                postRequest(name,address,subject,grade,username,password,image,usertype)
            }
        }
    }

    private fun postRequest(name: String, address: String, subject: String, grade: String, username: String, password: String, image: String,usertype:String) {
        //val url:String="http://192.168.8.102/senan/api/adduser.php"
        val url:String="https://sibauniversity.000webhostapp.com/adduser.php"
        val requestQueue:RequestQueue=Volley.newRequestQueue(this)
        val stringRequest:StringRequest= object: StringRequest(Request.Method.POST,url, Response.Listener { response ->
            Toast.makeText(applicationContext,response.trim().toString(),Toast.LENGTH_LONG).show()
            if (response.trim().equals("User Registerd Successfully")){
                var intent:Intent= Intent(this,DashboardActivity::class.java)
                startActivity(intent)
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(applicationContext,error.message,Toast.LENGTH_LONG).show()
        }){
            override fun getParams(): MutableMap<String, String>? {
                val params=HashMap<String,String>()
                params.put("name",name)
                params.put("location",address)
                params.put("image",image)
                params.put("subject",subject)
                params.put("grade",grade)
                params.put("username",username)
                params.put("password",password)
                params.put("usertype",usertype)
                return params
            }
        }
        requestQueue.add(stringRequest)
    }
}