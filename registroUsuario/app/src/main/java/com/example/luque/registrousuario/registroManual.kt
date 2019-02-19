package com.example.luque.registrousuario

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class registroManual : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_manual)
        var textoCorreo = findViewById(R.id.editTextCorreo) as TextView

        var textoContra = findViewById(R.id.editTextContraseña) as TextView

        var botonContinue = findViewById(R.id.button2) as Button
        botonContinue.setOnClickListener{
            var correoManual =textoCorreo.text.toString()
            var contraseña:String =textoContra.text.toString()
            val b : Bundle= Bundle()
            b.putString("datoCorreo",correoManual)
            b.putString("datoContraseña",contraseña)
            var intent100 = Intent(this, datosRegistro::class.java)
            //intent100.putExtra("correoManual", correoManual)
            //intent100.putExtra("correoContraseña",contraseña)
            intent100.putExtras(b)
            Toast.makeText(this, contraseña, Toast.LENGTH_LONG).show()
            startActivity(intent100)
        }

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
