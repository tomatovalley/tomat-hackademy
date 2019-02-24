package prueba.hackademi.eventos

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_emprendimientos.*
import kotlinx.android.synthetic.main.app_bar_emprendimientos.*
import kotlinx.android.synthetic.main.content_emprendimientos.*
import okhttp3.*
import java.io.IOException

class EmprendimientosActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val url = "http://157.230.182.120/eventos/crear_emprendimiento/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emprendimientos)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        RecyclerView_Emprendimientos_Usuario.layoutManager = LinearLayoutManager(this)
        fetchJson()


        fab3.setOnClickListener {
            if(crear_evento3.visibility == View.GONE && crear_emprendimiento3.visibility == View.GONE) {
                crear_evento3.visibility = View.VISIBLE
                crear_emprendimiento3.visibility = View.VISIBLE
            }else{
                crear_evento3.visibility = View.GONE
                crear_emprendimiento3.visibility = View.GONE
            }
        }
        crear_evento3.setOnClickListener{
            val intent:Intent = Intent(applicationContext, CrearEventoSA::class.java)
            intent.putExtra("eventolocacion", "Ubicacion")
            startActivity(intent)
        }

        crear_emprendimiento3.setOnClickListener {
            val intent:Intent = Intent(applicationContext, CrearEmprendimientoSA::class.java)
            startActivity(intent)
        }
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.moverse_eventos -> {
                val intent = Intent(applicationContext,MainActivity::class.java)
                val intent2 = Intent(applicationContext,EmprendimientosActivity::class.java)
                finish()
                startActivity(intent2)
                startActivity(intent)

            }
            R.id.moverse_emprendimientos -> {
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                } else {
                    super.onBackPressed()
                }
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    fun fetchJson(){

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = GsonBuilder().create()
                val homeFeed: Array<HomeFeed> = gson.fromJson(body, Array<HomeFeed>::class.java)
                runOnUiThread {
                    RecyclerView_Emprendimientos_Usuario.adapter = MainAdapter(homeFeed,false)
                    println(homeFeed)

                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failure")
            }
        })
    }

}

