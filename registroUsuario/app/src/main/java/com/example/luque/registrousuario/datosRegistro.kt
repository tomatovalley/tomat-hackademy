package com.example.luque.registrousuario

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.facebook.appevents.AppEventsLogger


class datosRegistro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_registro)

            //Variables donde se almacenan los datos de registro

            val nombreView= findViewById(R.id.textoNombre) as TextView
            val nombre: String = nombreView.text.toString()

            val apellidoView= findViewById(R.id.textoApellido) as TextView
            val apellido: String = apellidoView.text.toString()

            val usuarioView= findViewById(R.id.textoUsuario) as TextView
            val usuario: String = usuarioView.text.toString()

            var sexo : String
            val botonH= findViewById(R.id.botonHombre) as Button
            botonH.setOnClickListener{
                view -> sexo = "Hombre"
            }

            val botonM= findViewById(R.id.botonMujer) as Button
            botonM.setOnClickListener{
                view -> sexo = "Mujer"
            }

            val botonO= findViewById(R.id.botonOtro) as Button
            botonO.setOnClickListener{
                view -> sexo = "Otro"
            }

            var fecha : String

            val diaView= findViewById(R.id.textoDia) as TextView
            val dia: String = usuarioView.text.toString()

            val mesView= findViewById(R.id.textoMes) as TextView
            val mes: String = usuarioView.text.toString()

            val añoView= findViewById(R.id.textoAno) as TextView
            val año: String = usuarioView.text.toString()

            fecha = "${dia}/${mes}/${año}"

            var identidad : String
            val botonInv= findViewById(R.id.botonInversionista) as Button
            botonInv.setOnClickListener{
                view -> identidad = "Inversionista"
            }

            val botonPad= findViewById(R.id.botonPadawan) as Button
            botonPad.setOnClickListener{
                view -> identidad = "Padawan"
            }

            val botonEmp= findViewById(R.id.botonEmprendedor) as Button
            botonEmp.setOnClickListener{
                view -> identidad = "Emprendedor"
            }

            val botonEmpre= findViewById(R.id.botonEmpresario) as Button
            botonEmpre.setOnClickListener{
                view -> identidad = "Empresario"
            }

            val botonMen= findViewById(R.id.botonMentor) as Button
            botonMen.setOnClickListener{
                view -> identidad = "Mentor"
            }

            val botonEst= findViewById(R.id.botonEstudiante) as Button
            botonEst.setOnClickListener{
                view -> identidad = "Estudiante"
            }

            val trabajoView= findViewById(R.id.textoTrabajo) as TextView
            val trabajo: String = trabajoView.text.toString()


        val botonListo= findViewById(R.id.botonListo) as Button
        botonListo.setOnClickListener{
            val intent2 = Intent(this, login::class.java)
            startActivity(intent2)
        }



    }
}
