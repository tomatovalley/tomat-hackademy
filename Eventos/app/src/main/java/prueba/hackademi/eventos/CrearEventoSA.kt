package prueba.hackademi.eventos

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.widget.DatePicker
import android.widget.TextView
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.places.PlaceFilter
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import kotlinx.android.synthetic.main.crear_evento_sa1.*
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.*








class CrearEventoSA : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener  {
    var sdate: String? = null
    var fdate:String? = null
    var shour:String? = null
    var fhour:String? = null
    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val LAT_LNG_BOUNDS = LatLngBounds(
        LatLng(-40.0, -168.0), LatLng(71.0, 136.0)
    )
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mPlaceAutocompleteAdapter: PlaceAutocompleteAdapter? = null
    private var mPlaceFilter : PlaceFilter? = null
    val client2 = OkHttpClient()
    //var nombre:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_evento_sa1)
        val position : String = intent.getStringExtra("eventolocacion")
        if(position=="Ubicacion"){
            sitio_web_emprendimiento.hintTextColors
            sitio_web_emprendimiento.hint = position
        }else{
            sitio_web_emprendimiento.setText(position)
        }



    }

    override fun onStart() {
        super.onStart()
    }
    override fun onResume() {
        super.onResume()

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        var nMonth: Int = month.plus(1)

        hora_fin.text = SimpleDateFormat("HH:mm").format(calendar.time)


        var horaInicio: String

        dia_fecha_inicio.setText(""+day+"/"+nMonth+"/"+year)
        dia_fecha_fin.setText(""+day+"/"+nMonth+"/"+year)
        hora_inicio.text = SimpleDateFormat("HH:mm").format(calendar.time)

        boton_lugar.setOnClickListener{
            //Toast.makeText(this,"Ya salio",Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, MapaDeEvento::class.java)
            startActivity(intent)
        }

        //boton de fecha
        boton_fecha_inicio.setOnClickListener{

            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view: DatePicker?, mYear:Int, mMonth: Int, mDay: Int ->
                nMonth = mMonth.plus(1)
                var dia = "$mDay"
                var mes = "$nMonth"
                if(mMonth<10 || mDay<10){
                    if(mMonth<10){
                         mes = "0$nMonth"
                    }
                    if(mDay<10){
                         dia = "0$mDay"
                    }
                }
                dia_fecha_inicio.setText(""+dia+"/"+mes+"/"+mYear)
                val date ="$mYear-$mes-$dia"
                Date(date)

            },year,month,day)

            dpd.show()

        }



        boton_fecha_fin.setOnClickListener{
            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, mYear, mMonth,mDay ->
                nMonth = mMonth.plus(1)
                var dia = "$mDay"
                var mes = "$nMonth"
                if(mMonth<10 || mDay<10){
                    if(mMonth<10){
                        mes = "0$nMonth"
                    }
                    if(mDay<10){
                        dia = "0$mDay"
                    }
                }
                dia_fecha_fin.setText(""+dia+"/"+mes+"/"+mYear)
                val date ="$mYear-$mes-$dia"
                Date(y=date)

            },year,month,day)
            dpd.show()
        }


        //botones de hora
        boton_hora_inicio.setOnClickListener {

            val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker,mHour, mMinute ->

                calendar.set(Calendar.HOUR_OF_DAY, mHour)
                calendar.set(Calendar.MINUTE, mMinute)
                hora_inicio.text = SimpleDateFormat("HH:mm").format(calendar.time)
                Date(v=SimpleDateFormat("HH:mm").format(calendar.time))

            }
            TimePickerDialog(this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show()
        }


        boton_hora_fin.setOnClickListener {

            val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker,                  mHour, mMinute ->

                calendar.set(Calendar.HOUR_OF_DAY, mHour)
                calendar.set(Calendar.MINUTE, mMinute)
                hora_fin.text = SimpleDateFormat("HH:mm").format(calendar.time)
                Date(w=SimpleDateFormat("HH:mm").format(calendar.time))
            }
            TimePickerDialog(this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get
            (Calendar.MINUTE),true).show()
            //Toast.makeText(this, "$horaInicio",Toast.LENGTH_SHORT).show()
        }


        //va a la siguiente actividad
        boton_siguiente.setOnClickListener{

            val nombreView = findViewById<TextView>(R.id.nombre_emprendimiento)
            val registerEvento = nombreView.text.toString()

            val sedeView = findViewById<TextView>(R.id.sitio_web_emprendimiento)
            val registerSede:String = sedeView.text.toString()

            val detalle = findViewById<TextInputEditText>(R.id.texto_detalle)
            val registerDetalle:String = detalle.text.toString()


            val intent = Intent(applicationContext,CrearEventoSA2::class.java)
            intent.putExtra("registerSede",registerSede)
            intent.putExtra("registerEvento",registerEvento)

            intent.putExtra("registerSdate",sdate)
            intent.putExtra("registerFdate",fdate)

            intent.putExtra("registerShour",shour)
            intent.putExtra("registerFhour",fhour)

            intent.putExtra("registerDetalle",registerDetalle)

            startActivity(intent)

        }


    }
    fun Date (x:String?=sdate, y:String?=fdate, v:String?=shour, w:String?=fhour) {
        sdate = x
        fdate = y
        shour = v
        fhour = w
    }
}