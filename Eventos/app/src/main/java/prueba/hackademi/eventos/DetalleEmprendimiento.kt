package prueba.hackademi.eventos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalle_emprendimiento.*

class DetalleEmprendimiento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_emprendimiento)
        val detalleEmprendimiento = intent.getStringExtra("Emprendimiento")
        val detalleDescripcion = intent.getStringExtra("Descripcion")
        val image = intent.getStringExtra("Imagen")
        detalle_name_evento.text = detalleEmprendimiento
        descripcion_emprendimiento.text = detalleDescripcion
        Picasso.with(this).load(image).into(imagen_evento)
    }
}
