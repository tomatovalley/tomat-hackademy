package prueba.hackademi.eventos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        RecyclerView_Eventos_Usuario.layoutManager= LinearLayoutManager(this)
        RecyclerView_Eventos_Usuario.adapter= MainAdapter()


        fetchJson()

        }
    //aquí van las funciones
    fun fetchJson(){



        println("Estoy trantando de tomar un Json")

        //val url = "https://api.letsbuildthatapp.com/youtube/home_feed"
        val url = "http://10.112.31.201:3000/users"


        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                println(body)
                val gson = GsonBuilder().create()
                val homeFeed: Array<HomeFeed> = gson.fromJson(body, Array<HomeFeed>::class.java)
                /*var collectionType = object : TypeToken<Collection<ChannelSearchEnum>>() {

                }.type
                var enums = gson.fromJson(yourJson, collectionType)
                internal var enums = gson.fromJson(yourJson, Array<ChannelSearchEnum>::class.java)

                */

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failure")
            }
        })
    }


    }
class HomeFeed(evento: List<Evento>)

class Evento(eventos:String)