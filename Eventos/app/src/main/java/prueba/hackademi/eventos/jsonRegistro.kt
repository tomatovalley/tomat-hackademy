package com.example.luque.registrousuario



import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v4.content.ContextCompat.startActivity
import android.widget.Toast
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

class jsonRegistro        //constructor del hilo (Asynctask)
(//variables del hilo
        private var httpContext: Context,linkAPI: String,name: String, last_name: String, user_name: String, password:String,email:String,birthdate:String,gender:String) : AsyncTask<Void, Void, String>() {
    //name: String, last_name: String, user_name: String, password:String,email:String,birthdate:String,gender:String

    private var progressDialog: ProgressDialog?= null//dialogo cargando
    var resultadoapi = ""
    var linkrequestAPI = ""//link  para consumir el servicio rest
    var name:String
    var last_name:String
    var user_name:String
    var password:String
    var email:String
    var birthdate = "1997-07-20T00:00:00.000Z"
    var gender:String


    init {

        this.linkrequestAPI = linkAPI
        this.httpContext = httpContext
        this.name= name
        this.last_name= last_name
        this.user_name= user_name
        this.email= email
        this.password= password
        this.gender= gender
        this.birthdate= "1997-07-20T00:00:00.000Z"


    }



    /*
    class jsonRegistro (ctx: Context, linkAPI: String, name: String, last_name: String, user_name: String, email:String, password:String, gender:String, birthdate:String){
        this.linkrequestAPI = linkAPI
        this.name = name
        this.last_name = last_name
        this.user_name = user_name
        this.email = email
        this.password = password
        this.gender = gender
        this.birthdate = birthdate
    }
    */


    override fun onPreExecute() {
        super.onPreExecute()
        this.progressDialog = ProgressDialog.show(this.httpContext, "Procesando Solicitud", "por favor, espere")
    }

    override fun doInBackground(vararg params: Void): String? {
        var result: String? = ""

        val wsURL = this.linkrequestAPI
        var url: URL? = null
        try {
            // se crea la conexion al api: http://localhost:15009/WEBAPIREST/api/persona
            url = URL(wsURL)
            val urlConnection = url.openConnection() as HttpURLConnection
            //crear el objeto json para enviar por POST
            val parametrosPost = JSONObject()
            parametrosPost?.put("name", name)
            parametrosPost?.put("last_name", last_name)
            parametrosPost?.put("user_name", user_name)
            parametrosPost?.put("email", email)
            parametrosPost?.put("password",password)
            parametrosPost?.put("gender", gender)
            parametrosPost?.put("birthdate", birthdate)


            //DEFINIR PARAMETROS DE CONEXION
            urlConnection.readTimeout = 15000
            urlConnection.connectTimeout = 15000
            urlConnection.requestMethod = "POST"// se puede cambiar por delete ,put ,etc
            urlConnection.doInput = true
            urlConnection.doOutput = true


            //OBTENER EL RESULTADO DEL REQUEST
            val os = urlConnection.outputStream
            val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
            writer.write(getPostDataString(parametrosPost))
            writer.flush()
            writer.close()
            os.close()

            val responseCode = urlConnection.responseCode// conexion OK?
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                var hola :BufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream) as Reader?)

                val sb = StringBuffer("")
                var linea: String? = " "
                while ((linea == hola.readLine()) != null)
                {
                    sb.append(linea)
                    break

                }
                hola.close()
                result = sb.toString()
            } else {
                result = ("Error: $responseCode").toString()


            }


        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }


        return result

    }

    override fun onPostExecute(s: String) {
        super.onPostExecute(s)
        progressDialog?.dismiss()
        resultadoapi = s
        //Toast.makeText(httpContext, resultadoapi, Toast.LENGTH_LONG).show()//mostrara una notificacion con el resultado del request

        if(resultadoapi.equals("Error: 500")){
            Toast.makeText(httpContext, "Faltan campos por completar", Toast.LENGTH_LONG).show()
        }
        if(resultadoapi.equals("Error: 409")){
            Toast.makeText(httpContext, "Error el usuario ya estaba registrado", Toast.LENGTH_LONG).show()
        }
        if(resultadoapi.equals("")){

        }



    }
/*
    private fun Intento() {
        val intent100 = Intent(datosRegistro(), login::class.java)
        startActivity(intent100)

    }
    */


    //FUNCIONES----------------------------------------------------------------------
    //Transformar JSON Obejct a String *******************************************
    @Throws(Exception::class)
    fun getPostDataString(params: JSONObject): String {

        val result = StringBuilder()
        var first = true
        val itr = params.keys()
        while (itr.hasNext()) {

            val key = itr.next()
            val value = params.get(key)

            if (first)
                first = false
            else
                result.append("&")

            result.append(URLEncoder.encode(key, "UTF-8"))
            result.append("=")
            result.append(URLEncoder.encode(value.toString(), "UTF-8"))
        }
        return result.toString()
    }

}