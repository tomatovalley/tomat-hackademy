package com.example.luque.registrousuario


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class iniciosecion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciosecion)

        val botonInicioSecion= findViewById(R.id.botonInicioSecion) as Button
        botonInicioSecion.setOnClickListener{
            val intent3 = Intent(this, login::class.java)
            startActivity(intent3)
        }

        val botonRegistrarse= findViewById(R.id.botonRegistrarse) as Button
        botonRegistrarse.setOnClickListener{
            val intent5 = Intent(this, registroSocialMedia::class.java)
            startActivity(intent5)
        }
    }
}
