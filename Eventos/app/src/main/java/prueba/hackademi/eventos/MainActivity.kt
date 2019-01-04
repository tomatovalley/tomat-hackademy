package prueba.hackademi.eventos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

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

            println("Tratemos de recibir un Json")

        }



    }





