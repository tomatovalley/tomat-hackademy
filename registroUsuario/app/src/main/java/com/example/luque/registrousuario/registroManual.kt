package com.example.luque.registrousuario

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class registroManual : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_manual)

        var botonContinue = findViewById(R.id.button2) as Button
        botonContinue.setOnClickListener{
            var intent100 = Intent(this, datosRegistro::class.java)
            startActivity(intent100)
        }
        val textoContra= findViewById(R.id.editText2) as TextView
        val check = findViewById(R.id.checkBox) as CheckBox
        var valor = true
        check.setOnClickListener{
            if(valor==true){
                textoContra.setInputType(InputType.TYPE_CLASS_TEXT);

                valor=false
            }
            else{
                textoContra.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                valor=true
            }
        }

    }
}
