package com.example.luque.registrousuario

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.facebook.AccessToken
import com.facebook.FacebookSdk
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import android.content.Intent



class eventos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos)
        var btnLogout = findViewById(R.id.btnLogout) as Button
        btnLogout.setOnClickListener{
            LoginManager.getInstance().logOut()
            goLoginScreen()
        }


    }

    private fun goLoginScreen()
    {
        val intent = Intent(this, registroSocialMedia::class.java)

        startActivity(intent)
    }
}
