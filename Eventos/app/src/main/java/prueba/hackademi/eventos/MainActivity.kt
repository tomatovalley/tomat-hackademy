package prueba.hackademi.eventos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

val url = "http://127.0.0.1:8000/eventos/detalles_evento/   "

class MainActivity : AppCompatActivity() {

    lateinit var crearEvento : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        crearEvento = findViewById(R.id.crear_evento)


        RecyclerView_Eventos_Usuario.layoutManager = LinearLayoutManager(this)
        fetchJson()
        crear_evento.setOnClickListener{
            val intent:Intent = Intent(applicationContext, CrearEventoSA::class.java)
            intent.putExtra("eventolocacion", "Ubicacion")
            startActivity(intent)
        }
        boton_ticket.setOnClickListener {
            crear_evento.visibility = View.GONE
        }

        fab.setOnClickListener {
            if(crear_evento.visibility == View.GONE) {
                crear_evento.visibility = View.VISIBLE
            }else crear_evento.visibility = View.GONE
        }



    }

    //aquí van las funciones
    fun fetchJson(){

        println("Estoy trantando de tomar un Json")

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = GsonBuilder().create()
                val homeFeed: Array<HomeFeed> = gson.fromJson(body, Array<HomeFeed>::class.java)
                runOnUiThread {
                    RecyclerView_Eventos_Usuario.adapter = MainAdapter(homeFeed)
                    println(homeFeed)

                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failure")
            }
        })
    }


    }
class HomeFeed(val evento:String, val sede:String)