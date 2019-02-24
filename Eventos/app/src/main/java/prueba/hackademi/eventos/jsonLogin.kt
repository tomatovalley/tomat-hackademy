package com.example.luque.registrousuario



import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

class jsonLogin        //constructor del hilo (Asynctask)
(//variables del hilo
        private var httpContext: Context,linkAPI: String,password:String,email:String) : AsyncTask<Void, Void, String>() {

    private var progressDialog: ProgressDialog?= null//dialogo cargando
    var resultadoapi = ""
    var linkrequestAPI = ""//link  para consumir el servicio rest
    var email:String
    var password:String




    init {
        this.linkrequestAPI = linkAPI
        this.httpContext = httpContext
        this.email= email
        this.password= password
    }

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
            parametrosPost?.put("email", email)
            parametrosPost?.put("password",password)



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
        Toast.makeText(httpContext, resultadoapi, Toast.LENGTH_LONG).show()//mostrara una notificacion con el resultado del request

    }

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