package com.chamara.senan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InstituteOrUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_institute_or_user)

        val btnUsers:Button=findViewById(R.id.btnUsers);
        val btnInstitutes:Button=findViewById(R.id.btnInstitutes)

        btnUsers.setOnClickListener {
            val intent: Intent = Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
        }

        btnInstitutes.setOnClickListener {
            val intent:Intent= Intent(applicationContext,InstituteLoginActivity::class.java)
            startActivity(intent)
        }
    }
}