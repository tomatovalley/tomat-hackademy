package prueba.hackademi.eventos

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.crear_evento_sa3.*
import okhttp3.*
import java.io.IOException
val url2 = "http://157.230.182.120/eventos/crear_evento/"
class CrearEventoSA3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_evento_sa3)


        val registerSede= intent.getStringExtra("registerSede")
        val registerEvento = intent.getStringExtra("registerEvento")
        val sdate = intent.getStringExtra("registerSdate")
        val fdate = intent.getStringExtra("registerFdate")
        val shour = intent.getStringExtra("registerShour")
        val fhour = intent.getStringExtra("registerFhour")
        val registerDetalle = intent.getStringExtra("registerDetalle")
        //val foto= intent.getStringExtra("Foto")

        val client2 = OkHttpClient()

        val requestDeChequeo = Request.Builder().url(url2).build()

        var clienteConectado: Boolean = false

        var asistentes :Int

        client2.newCall(requestDeChequeo).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                clienteConectado = true
            }

            override fun onFailure(call: Call, e: IOException) {

            }


        })

        capacidad_mayor.setOnClickListener {

            asistentes = capacidad_asistentes.text.toString().toInt()

            asistentes += 50

            capacidad_asistentes.setText(asistentes.toString())
        }

        capacidad_menor.setOnClickListener {

            asistentes = capacidad_asistentes.text.toString().toInt()

            asistentes -= 50

            capacidad_asistentes.setText(asistentes.toString())
        }

        salir.setOnClickListener {

            val organizador = findViewById<TextView>(R.id.registrar_organizador)
            val registerOrganizador = organizador.text.toString()

            if (clienteConectado){

                val json = """{
    "username": null,
    "name": "$registerEvento",
    "place": "$registerSede",
    "begin_date": "$sdate",
    "image": null,
    "start_hour": "$shour",
    "final_date": "$fdate",
    "end_hour": "$fhour",
    "description": "$registerDetalle",
    "organizer": "$registerOrganizador"
}""".trimIndent()

                val body= RequestBody.create(MediaType.parse("application/json; charset=utf-8"),                json)
                val request= Request.Builder().url(url2).post(body).build()

                var estadoDeRegistro: Boolean = true


                client2.newCall(request).enqueue(object: Callback {

                    override fun onResponse(call: Call, response: Response) {
                        println("Register made")

                    }

                    override fun onFailure(call: Call, e: IOException) {
                        estadoDeRegistro = false
                        println("Failure, event not register")
                    }

                })


                Handler().postDelayed(
                    {
                        if (estadoDeRegistro){
                            Toast.makeText(this,"Registro realizado",Toast.LENGTH_SHORT).show()
                        } else{
                            Toast.makeText(this,"Registro fallido",Toast.LENGTH_SHORT).show()
                        }

                    }
                    ,50
                )

                val intent = Intent(applicationContext,CrearEventoSA4::class.java)
                startActivity(intent)

            }
            else{
                Toast.makeText(applicationContext,"No se puede conectar con el servidor",Toast  .LENGTH_SHORT).show()
                /*val intent = Intent(applicationContext,CrearEventoSA4::class.java)
                startActivity(intent)
                */
                //Esto va en el registro de evento realizado, esto es para una prueba
                /*val fb: String = findViewById<TextView>(R.id.registrar_facebook).text.toString()
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, fb )
                intent.type = "text/plain"

                startActivity(Intent.createChooser(intent, "Compartir con: "))*/

            }

        }

    }
}
