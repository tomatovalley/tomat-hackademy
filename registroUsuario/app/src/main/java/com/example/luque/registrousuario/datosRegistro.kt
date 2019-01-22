package com.example.luque.registrousuario

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import kotlinx.android.synthetic.main.activity_datos_registro.*
import java.util.*




class datosRegistro : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    //INICIO DE SESION CON GOOGLE
    private var googleApiClient : GoogleApiClient? = null //IG
    private var photoImageView: ImageView? = null //IG
    private var textoNombre: TextView? = null //IG
    private var textoApellido: TextView? = null //IG
    private var correo : TextView? = null
    private var id : TextView? = null
    //CAMPOS NORMALES
    private var textoUsuario: TextView? = null
    private var trabajoView: TextView? = null
    private var sexo: String? =null
    private var identidad : String? = null
    //calendar
    val TAG = "datosRegistro"
    private var mDisplayDate: TextView? = null
    private var mDateSetListener: DatePickerDialog.OnDateSetListener? = null
    private var date : String? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_registro)
        //VARIABLES DE KOTLIN INICIALIZADAS CON VALOR AL COMPONENTE XML
        mDisplayDate = findViewById(R.id.textViewFecha) as TextView
        textoNombre = findViewById(R.id.textoNombre) as TextView
        textoApellido = findViewById(R.id.textoApellido) as TextView
        textoUsuario = findViewById(R.id.textoUsuario) as TextView
        trabajoView = findViewById(R.id.textoTrabajo) as TextView

        //OYENTES DE LOS BOTONES(MANEJADOR DE EVENTOS)
        val botonH= findViewById(R.id.botonHombre) as Button
        botonH.setOnClickListener{
            view -> sexo = "Hombre"
            botonSeleccionadoSexo(sexo!!)
        }

        val botonM= findViewById(R.id.botonMujer) as Button
        botonM.setOnClickListener{
            view -> sexo = "Mujer"
            botonSeleccionadoSexo(sexo!!)
        }

        val botonO= findViewById(R.id.botonOtro) as Button
        botonO.setOnClickListener{
            view -> sexo = "Otro"
            botonSeleccionadoSexo(sexo!!)
        }

        val botonInv= findViewById(R.id.botonInversionista) as Button
        botonInv.setOnClickListener{
            view -> identidad = "Inversionista"
            botonSeleccionadoIdentidad(identidad!!)
        }

        val botonPad= findViewById(R.id.botonPadawan) as Button
        botonPad.setOnClickListener{
            view -> identidad = "Padawan"
            botonSeleccionadoIdentidad(identidad!!)
        }

        val botonEmp= findViewById(R.id.botonEmprendedor) as Button
        botonEmp.setOnClickListener{
            view -> identidad = "Emprendedor"
            botonSeleccionadoIdentidad(identidad!!)
        }

        val botonEmpre= findViewById(R.id.botonEmpresario) as Button
        botonEmpre.setOnClickListener{
            view -> identidad = "Empresario"
            botonSeleccionadoIdentidad(identidad!!)
        }

        val botonMen= findViewById(R.id.botonMentor) as Button
        botonMen.setOnClickListener{
            view -> identidad = "Mentor"
            botonSeleccionadoIdentidad(identidad!!)
        }

        val botonEst= findViewById(R.id.botonEstudiante) as Button
        botonEst.setOnClickListener{
            view -> identidad = "Estudiante"
            botonSeleccionadoIdentidad(identidad!!)
        }


        //SUBRAYA EL TEXTO DEL BOTON DE LA FECHA
        val mitextoU = SpannableString(mDisplayDate?.text)
        mitextoU.setSpan(UnderlineSpan(), 0, mitextoU.length, 0)
        mDisplayDate?.text = mitextoU

        //MUESTRA EL VENTANA DE LA FECHA EL PRESIONAR EL BOTON SELECCIONA LA FECHA
        mDisplayDate?.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(
                    this@datosRegistro,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListener,
                    year, month, day)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }

        mDateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            var month = month
            month = month + 1
            Log.d(TAG, "onDateSet: mm/dd/yyy: $month/$day/$year")

            this.date = month.toString() + "/" + day + "/" + year
            mDisplayDate?.setText(date)
        }

        //INICIO DE SESION CON GOOGLE
        photoImageView = findViewById(R.id.photoImageView) as ImageView
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        googleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi<GoogleSignInOptions?>(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()





















        val botonListo= findViewById(R.id.botonListo) as Button
        botonListo.setOnClickListener{
            val intent2 = Intent(this, login::class.java)
            startActivity(intent2)
            LoginManager.getInstance().logOut()
            goLoginScreen()
            logOutGoogle()
            //Variables donde se almacenan los datos de registro
            val idFinal : String = id.toString()
            val correoFinal : String = correo.toString()
            val nombre: String = textoNombre?.text.toString()
            val apellido: String = textoApellido?.text.toString()
            val usuario: String = textoUsuario?.text.toString()
            val sexoFinal: String = sexo.toString()
            val fecha: String = date.toString()
            val identidadFinal :String = identidad.toString()
            val trabajo: String = trabajoView?.text.toString()
        }




    }
    //INICIO DE SESION CON GOOGLE
    //IG







    fun botonSeleccionadoSexo(tipoSexo : String){
        if (tipoSexo.equals("Hombre")){
            this.botonHombre.setBackgroundResource(R.drawable.fondobotonverde)
            this.botonMujer.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonOtro.setBackgroundResource(R.drawable.boton_redondeado)
        }
        else if (tipoSexo.equals("Mujer")){
            this.botonMujer.setBackgroundResource(R.drawable.fondobotonverde)
            this.botonHombre.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonOtro.setBackgroundResource(R.drawable.boton_redondeado)
        }
        else if(tipoSexo.equals("Otro")){
            this.botonMujer.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonHombre.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonOtro.setBackgroundResource((R.drawable.fondobotonverde))
        }
    }

    fun botonSeleccionadoIdentidad(identidad : String){
        if (identidad.equals("Inversionista")){
            this.botonInversionista.setBackgroundResource(R.drawable.fondobotonverde)
            this.botonPadawan.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEmprendedor.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEmpresario.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonMentor.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEstudiante.setBackgroundResource(R.drawable.boton_redondeado)
        }
        else if (identidad.equals("Padawan")){
            this.botonInversionista.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonPadawan.setBackgroundResource(R.drawable.fondobotonverde)
            this.botonEmprendedor.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEmpresario.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonMentor.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEstudiante.setBackgroundResource(R.drawable.boton_redondeado)
        }
        else if(identidad.equals("Emprendedor")){
            this.botonInversionista.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonPadawan.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEmprendedor.setBackgroundResource(R.drawable.fondobotonverde)
            this.botonEmpresario.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonMentor.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEstudiante.setBackgroundResource(R.drawable.boton_redondeado)
        }
        else if(identidad.equals("Empresario")){
            this.botonInversionista.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonPadawan.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEmprendedor.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEmpresario.setBackgroundResource(R.drawable.fondobotonverde)
            this.botonMentor.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEstudiante.setBackgroundResource(R.drawable.boton_redondeado)
        }
        else if(identidad.equals("Mentor")){
            this.botonInversionista.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonPadawan.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEmprendedor.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEmpresario.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonMentor.setBackgroundResource(R.drawable.fondobotonverde)
            this.botonEstudiante.setBackgroundResource(R.drawable.boton_redondeado)
        }
        else if(identidad.equals("Estudiante")){
            this.botonInversionista.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonPadawan.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEmprendedor.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEmpresario.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonMentor.setBackgroundResource(R.drawable.boton_redondeado)
            this.botonEstudiante.setBackgroundResource(R.drawable.fondobotonverde)
        }
    }
    private fun goLoginScreen()
    {
        val intent = Intent(this, registroSocialMedia::class.java)

        startActivity(intent)
    }

    //IG
    override fun onStart() {
        super.onStart()

        val opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient)
        if (opr.isDone) {
            val result = opr.get()
            handleSignInResult(result)
        } else {
            opr.setResultCallback(object : ResultCallback<GoogleSignInResult> {
                override fun onResult(googleSignInResult: GoogleSignInResult) {
                    handleSignInResult(googleSignInResult)
                }
            })
        }
    }
    //IG
    private fun handleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {


            val account = result.signInAccount
            //emailTextView?.setText(account?.email)
            textoNombre?.setText(account?.givenName)
            textoApellido?.setText(account?.familyName)
            textoUsuario?.setText(account?.email)
            id?.setText(account?.id)
            correo?.setText(account?.email)

            Glide.with(this).load(account?.photoUrl).into(photoImageView)
            if(account?.photoUrl == null){
                photoImageView?.setBackgroundResource(R.drawable.iconoregistro)
            }


        } else {
            goLogInScreen()
        }
    }
    //IG
    private fun goLogInScreen() {
        //val intent5 = Intent(this, login::class.java)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        //startActivity(intent5)
    }
    //IG
    fun logOutGoogle() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(object : ResultCallback<Status> {
            override fun onResult(status: Status) {
                if (status.isSuccess()) {
                    goLogInScreen()
                } else {
                    Toast.makeText(applicationContext, "R.string.not_close_session", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    //IG
    fun revoke(view: View) {
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(object : ResultCallback<Status> {
            override fun onResult(status: Status) {
                if (status.isSuccess()) {
                    goLogInScreen()
                } else {
                    Toast.makeText(applicationContext, "R.string.not_revoke", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    //IG
    override fun onConnectionFailed(connectionResult: ConnectionResult) {

    }
}


