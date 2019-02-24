package prueba.hackademi.eventos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.crear_emprendimiento_sa.*

class CrearEmprendimientoSA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_emprendimiento_sa)

        ok_crear_emprendimiento.setOnClickListener {
            val intent = Intent(applicationContext,CrearEmprendimientoSA2::class.java)
            startActivity(intent)
        }

    }
}
