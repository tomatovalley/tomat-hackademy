package prueba.hackademi.eventos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_crear_evento_sa4.*

class CrearEventoSA4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_evento_sa4)

        boton_redes_sociales.setOnClickListener {

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Prros")
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Compartir en: "))

        }

        boton_nueva_activity.setOnClickListener{

            val intent = Intent(applicationContext,MainActivity::class.java)
            val intent2 = Intent(applicationContext,CrearEventoSA::class.java)
            intent2.putExtra("eventolocacion","Ubicacion")
            startActivity(intent)
            startActivity(intent2)
            finishAffinity()
        }

        boton_inicio.setOnClickListener{
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }


    }
}
