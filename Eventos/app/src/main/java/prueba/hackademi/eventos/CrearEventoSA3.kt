package prueba.hackademi.eventos

import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.crear_evento_sa3.*
import okhttp3.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
val urlEventosPublicar = "http://192.168.10.54:8000/eventos/crear_evento/"
//val urlEventosPublicar = "http://157.230.182.120/eventos/crear_evento/"
var estadoDeRegistro: Boolean = true

class CrearEventoSA3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_evento_sa3)

        val image = intent.getStringExtra("Image")
        // esta función es para pasar imagenes entre
        // Glide.with(this).load(image).into(aa)
        val registerSede= intent.getStringExtra("registerSede")
        val registerEvento = intent.getStringExtra("registerEvento")
        val sdate = intent.getStringExtra("registerSdate")
        val fdate = intent.getStringExtra("registerFdate")
        val shour = intent.getStringExtra("registerShour")
        val fhour = intent.getStringExtra("registerFhour")
        val registerDetalle = intent.getStringExtra("registerDetalle")
        //val foto= intent.getStringExtra("Foto")

        val client2 = OkHttpClient()

        val requestDeChequeo = Request.Builder().url(urlEventosPublicar).build()

        var clienteConectado: Boolean = false

        var asistentes :Int

        client2.newCall(requestDeChequeo).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                clienteConectado = true
                salir.setBackgroundColor(resources.getColor(R.color.color_ok))
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

            val organizador = findViewById<TextView>(R.id.nombre_emprendimiento)
            val registerOrganizador = organizador.text.toString()

            if (clienteConectado){
                val sendImage = imageHandler(image)

                val json = """{
    "username": null,
    "name": "$registerEvento",
    "place": "$registerSede",
    "begin_date": "$sdate",
    "image": "$sendImage",
    "start_hour": "$shour",
    "final_date": "$fdate",
    "end_hour": "$fhour",
    "description": "$registerDetalle",
    "organizer": "$registerOrganizador"
}""".trimIndent()

                val body= RequestBody.create(MediaType
                    .parse("application/json; charset=utf-8"),json)
                val request= Request.Builder().url(urlEventosPublicar).post(body).build()


                client2.newCall(request).enqueue(object: Callback {

                    override fun onResponse(call: Call, response: Response) {
                        println("Register made")

                    }

                    override fun onFailure(call: Call, e: IOException) {
                        funcionparasacar(false)
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
    fun funcionparasacar(x:Boolean=true){
        estadoDeRegistro = x
    }
    fun imageHandler(image:String):File {

        val bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(image))
        val file = File(applicationContext.cacheDir,"Imagenpro")
        file.createNewFile()

        //val imageResult = BitmapFactory.decodeFile(image)
        val bao= ByteArrayOutputStream()
        //imageResult.compress(Bitmap.CompressFormat.JPEG,100,bao)
        //return bao
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bao)
        val bb = bao.toByteArray()

        val fOut = FileOutputStream(file)
        fOut.write(bb)
        fOut.flush()
        fOut.close()

        /*val sendImage = Base64.encodeToString(bb,Base64.DEFAULT)
        print(sendImage)*/
        return file

    }
}
