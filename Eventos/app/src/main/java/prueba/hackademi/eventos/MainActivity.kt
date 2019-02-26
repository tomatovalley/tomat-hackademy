package prueba.hackademi.eventos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_emprendimientos.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_emprendimientos.*
import okhttp3.*
import java.io.IOException

//val urlEventos = "http://192.168.10.65:8000/eventos/detalles_evento/"
val urlEventos= "http://157.230.182.120/eventos/detalles_evento/"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var crearEvento : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)



        crearEvento = findViewById(R.id.crear_evento)

        RecyclerView_Emprendimientos_Usuario.layoutManager = LinearLayoutManager(this)
        fetchJson()
        crearEvento.setOnClickListener{
            val intent:Intent = Intent(applicationContext, CrearEventoSA::class.java)
            intent.putExtra("eventolocacion", "Ubicacion")
            startActivity(intent)
        }
        boton_ticket.setOnClickListener {
            crear_evento.visibility = View.GONE
            crear_emprendimiento.visibility = View.GONE
        }

        fab.setOnClickListener {
            if(crear_evento.visibility == View.GONE && crear_emprendimiento.visibility == View.GONE) {
                crear_evento.visibility = View.VISIBLE
                crear_emprendimiento.visibility = View.VISIBLE
            }else{
                crear_evento.visibility = View.GONE
             crear_emprendimiento.visibility = View.GONE
            }
        }
        crear_emprendimiento.setOnClickListener {
            val intent = Intent(applicationContext,CrearEmprendimientoSA::class.java)
            startActivity(intent)
        }



    }

    //aquí van las funciones
    fun fetchJson(){

        val request = Request.Builder().url(urlEventos).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = GsonBuilder().create()
                val homeFeed: Array<HomeFeed> = gson.fromJson(body, Array<HomeFeed>::class.java)
                runOnUiThread {
                    RecyclerView_Emprendimientos_Usuario.adapter = MainAdapter(homeFeed,true)
                    println(homeFeed)

                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failure")
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.moverse_eventos -> {
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                } else {
                    super.onBackPressed()
                }
            }
            R.id.moverse_emprendimientos -> {
                val intent = Intent(applicationContext,EmprendimientosActivity::class.java)
                val intent2 = Intent(applicationContext,MainActivity::class.java)
                finish()
                startActivity(intent2)
                startActivity(intent)

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }





}
class HomeFeed(val name:String, val place:String, var image:String, val description:String)