package com.example.luque.registrousuario

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detalle_evento.*

class DetalleEvento: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_evento)

        val detalleEvento = intent.getStringExtra("Evento")
        val detalleUbicacion = intent.getStringExtra("Sede")

        detalle_name_evento.text = detalleEvento
        detalle_ubicacion.text = detalleUbicacion


        Toast.makeText(applicationContext,"Funciono",Toast.LENGTH_SHORT).show()
        println("gg")



    }

}
