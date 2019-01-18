package prueba.hackademi.eventos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.crear_evento_sa3.*
import okhttp3.*
import java.io.IOException

class CrearEventoSA3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_evento_sa3)

        val registerSede= intent.getStringExtra("registerSede")
        val registerEvento = intent.getStringExtra("registerEvento")
        val client2 = OkHttpClient()

        val requestDeChequeo = Request.Builder().url(url).build()

        var clienteConectado: Boolean = false

        var asistentes :Int

        client2.newCall(requestDeChequeo).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                clienteConectado = true
            }

            override fun onFailure(call: Call, e: IOException) {

            }


        })


        activar_redes_sociales.setOnClickListener {

            if (activar_redes_sociales.isChecked){
                registrar_facebook.visibility = View.VISIBLE
                registrar_instagram.visibility = View.VISIBLE
                registrar_twitter.visibility = View.VISIBLE
                tags.visibility = View.VISIBLE
            }
            else{
                registrar_facebook.visibility = View.GONE
                registrar_instagram.visibility = View.GONE
                registrar_twitter.visibility = View.GONE
                tags.visibility = View.GONE

            }

        }

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

            if (clienteConectado){

                val json = """
                {
                    "evento":"$registerEvento",
                    "sede":"$registerSede"
                }
            """.trimIndent()

                val body= RequestBody.create(MediaType.parse("application/json; charset=utf-8"),                json)
                val request= Request.Builder().url(url).post(body).build()

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

                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)

            }
            else{ Toast.makeText(applicationContext,"No se puede conectar con el servidor",Toast  .LENGTH_SHORT).show()}



        }

    }
}
