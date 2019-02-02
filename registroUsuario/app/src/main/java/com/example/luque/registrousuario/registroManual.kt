package com.example.luque.registrousuario

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class registroManual : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_manual)

        var botonContinue = findViewById(R.id.button2) as Button
        botonContinue.setOnClickListener{
            var intent100 = Intent(this, datosRegistro::class.java)
            startActivity(intent100)
        }

    }
}
