package prueba.hackademi.eventos

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.crear_evento_sa1.*
import okhttp3.*
import okhttp3.RequestBody;
import okhttp3.Response
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class CrearEventoSA : AppCompatActivity() {

    val client2 = OkHttpClient()
    //var nombre:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_evento_sa1)


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

        if(calendar.get(Calendar.MINUTE) < 10){

            val minute = calendar.get(Calendar.MINUTE)+1
            hora_fin.setText(""+hour+":"+0+minute)

        }else {

            val minute = calendar.get(Calendar.MINUTE) + 1
            hora_fin.setText("" + hour + ":" + minute)

        }


        var horaInicio: String

        dia_fecha_inicio.setText(""+day+"/"+month+"/"+year)
        dia_fecha_fin.setText(""+day+"/"+month+"/"+year)
        hora_inicio.text = SimpleDateFormat("HH:mm").format(calendar.time)

        //boton de fecha

        boton_fecha_inicio.setOnClickListener{

            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view: DatePicker?, mYear:            Int,  mMonth: Int, mDay: Int ->
                dia_fecha_inicio.setText(""+mDay+"/"+mMonth+"/"+mYear)
            },year,month,day)

            dpd.show()
        }



        boton_fecha_fin.setOnClickListener{
            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, mYear, mMonth,                   mDay ->

                dia_fecha_fin.setText(""+mDay+"/"+mMonth+"/"+mYear)

            },year,month,day)
            dpd.show()
        }

        //botones de hora



        boton_hora_inicio.setOnClickListener {

            val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker,mHour, mMinute ->

                calendar.set(Calendar.HOUR_OF_DAY, mHour)
                calendar.set(Calendar.MINUTE, mMinute)
                hora_inicio.text = SimpleDateFormat("HH:mm").format(calendar.time)
                horaInicio = SimpleDateFormat("HH:mm").format(calendar.time)
            }
            TimePickerDialog(this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get                   (Calendar.MINUTE),true).show()
        }



        boton_hora_fin.setOnClickListener {

            val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker,                  mHour, mMinute ->

                calendar.set(Calendar.HOUR_OF_DAY, mHour)
                calendar.set(Calendar.MINUTE, mMinute)
                hora_fin.text = SimpleDateFormat("HH:mm").format(calendar.time)
            }
            TimePickerDialog(this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get
            (Calendar.MINUTE),true).show()
            //Toast.makeText(this, "$horaInicio",Toast.LENGTH_SHORT).show()
        }

        //va a la siguiente actividad


        boton_siguiente.setOnClickListener{

            val nombreView = findViewById(R.id.registrar_organizador) as TextView
            val registerEvento = nombreView.text.toString()

            val sedeView = findViewById(R.id.register_sede) as TextView
            val registerSede:String = sedeView.text.toString()


            val intent = Intent(applicationContext,CrearEventoSA2::class.java)
            intent.putExtra("registerSede",registerSede)
            intent.putExtra("registerEvento",registerEvento)
            startActivity(intent)

        }

    }

}