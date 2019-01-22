package com.example.luque.registrousuario


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button


class iniciosecion : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciosecion)




            val botonInicioSecion= findViewById(R.id.botonInicioSecion) as Button
            botonInicioSecion.setOnClickListener {
                var intent = Intent(this, login::class.java)
                startActivity(intent)

            }


            val botonregistrarse= findViewById<Button>(R.id.botonRegistrarse)
            botonregistrarse.setOnClickListener{

                var intent2 = Intent(this, registroSocialMedia::class.java)
                startActivity(intent2)

            }






    }
}
