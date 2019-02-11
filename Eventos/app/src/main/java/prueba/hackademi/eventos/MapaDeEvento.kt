package prueba.hackademi.eventos

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.mapa_de_evento.*

class MapaDeEvento : AppCompatActivity(), OnMapReadyCallback {

    var mm: Marker? = null


    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mapa_de_evento)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap){
        mMap = googleMap

        mMap.setOnMapLongClickListener {
            createMarker(it)
        }

        val casaJo = LatLng(24.8017560,-107.3503810)
        createMarker(casaJo)

        boton_siguiente.setOnClickListener {
            val gg = mm?.position.toString()
            val intent = Intent(applicationContext,CrearEventoSA::class.java)
            intent.putExtra("eventolocacion",gg)
            startActivity(intent)
        }
    }

    fun createMarker(latLng: LatLng) {
        //En esta función creamos marcadores y borramos el que había antes de este
        mm?.remove()
        mm=mMap.addMarker(MarkerOptions().position(latLng))
        mm
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,14f))

    }
}
