package com.example.wiseathon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_s_login_main.*

class MainActivity : AppCompatActivity() {
    var txtRoleSelect = ""
    var txtUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this@MainActivity,SLoginMain::class.java)
        loginSubmit.setOnClickListener {
            txtUser = textUser.text.toString()
            var radio : RadioButton
            radio = findViewById(radioSelector.checkedRadioButtonId)
           txtRoleSelect = radio.text.toString()
            intent.putExtra("User", "Tim")//txtUser)
            intent.putExtra("Role", "Student")//txtRoleSelect)
            startActivity(Intent(this, SLoginMain::class.java))
        }
    }
}
