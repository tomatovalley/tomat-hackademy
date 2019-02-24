package prueba.hackademi.eventos

import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detalle_evento.*

class DetalleEvento: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalle_evento)

        val detalleEvento = intent.getStringExtra("Evento")
        val detalleUbicacion = intent.getStringExtra("Sede")
        val detalleDescription = intent.getStringExtra("Descripcion")
        val image = intent.getStringExtra("Imagen")

        detalle_name_evento.text = detalleEvento
        detalle_ubicacion.text = detalleUbicacion
        detalle_descripcion.text = detalleDescription
        //obtenemos y seteamos la imagen
        Picasso.with(this).load(image).into(imagen_evento)


    }

}