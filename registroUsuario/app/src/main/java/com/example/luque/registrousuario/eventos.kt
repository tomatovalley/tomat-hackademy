@file:Suppress("DEPRECATION")

package com.example.luque.registrousuario

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.facebook.login.LoginManager


class eventos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos)
        var btnLogout = findViewById(R.id.btnLogout) as Button

        btnLogout.setOnClickListener{
            LoginManager.getInstance().logOut()


        }



    }

}
