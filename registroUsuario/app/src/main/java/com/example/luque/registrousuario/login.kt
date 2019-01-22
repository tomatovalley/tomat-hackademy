package com.example.luque.registrousuario

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val textoContraseña= findViewById(R.id.textoContraseña) as TextView
        var valor = true
        botonOjo.setOnClickListener{
            if(valor == true){
                textoContraseña.setInputType(InputType.TYPE_CLASS_TEXT);
                botonOjo.setBackgroundResource(R.drawable.ojocontra)
                valor=false
            }
            else{
                textoContraseña.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                botonOjo.setBackgroundResource(R.drawable.ojocontradesac2)
                valor=true
            }
        }

        val botonlogin= findViewById(R.id.botonlogin) as Button
        botonlogin.setOnClickListener{
            val intent3 = Intent(this, eventos::class.java)
            startActivity(intent3)
        }



    }
}
