package prueba.hackademi.eventos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.crear_emprendimiento_sa2.*

class CrearEmprendimientoSA2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_emprendimiento_sa2)

        boton_nueva_activity2.setOnClickListener{

            val intent = Intent(applicationContext,MainActivity::class.java)
            val intent2 = Intent(applicationContext,CrearEmprendimientoSA::class.java)
            intent2.putExtra("eventolocacion","Ubicacion")
            startActivity(intent)
            startActivity(intent2)
            finishAffinity()
        }

        boton_inicio2.setOnClickListener{
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

    }
}
