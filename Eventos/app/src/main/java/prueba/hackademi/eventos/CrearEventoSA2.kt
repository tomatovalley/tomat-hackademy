package prueba.hackademi.eventos

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.crear_evento_sa1.*
import kotlinx.android.synthetic.main.crear_evento_sa2.*
import kotlinx.android.synthetic.main.detalle_evento.*
import kotlinx.android.synthetic.main.eventos_row.*
import java.util.jar.Manifest

class CrearEventoSA2 : AppCompatActivity(){

    private val PERMISSION_CODE: Int = 1000
    var image_rui: Uri? = null
    val IMAGE_CAPTURE_CODE : Int = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_evento_sa2)

        val registerSede= intent.getStringExtra("registerSede")
        val registerEvento = intent.getStringExtra("registerEvento")


        agregar_imagen.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager   .PERMISSION_DENIED || checkSelfPermission(android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED) {
                    //permiso denegado
                    val permission = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

                    requestPermissions(permission, PERMISSION_CODE)

                }
                else{
                    abrirCamara()
                }

            }
            else{
                abrirCamara()
            }

        }


        button_next2.setOnClickListener{
            val intent = Intent(applicationContext,CrearEventoSA3::class.java)
            intent.putExtra("registerSede",registerSede)
            intent.putExtra("registerEvento",registerEvento)
            startActivity(intent)
        }

        boton_de_repuesto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager   .PERMISSION_DENIED || checkSelfPermission(android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED) {
                    //permiso denegado
                    val permission = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

                    requestPermissions(permission, PERMISSION_CODE)

                }
                else{
                    abrirCamara()
                }

            }
            else{
                abrirCamara()
            }
        }

    }



    fun abrirCamara(){

        val valores = ContentValues()

        valores.put(MediaStore.Images.Media.TITLE,"New picture")
        valores.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")

        image_rui = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,valores)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_rui)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)



    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    abrirCamara()

                }else{
                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       if (resultCode == Activity.RESULT_OK){

           agregar_imagen.visibility = View.INVISIBLE
           imagen_de_boton.setImageURI(image_rui)
           imagen_de_boton.visibility = View.VISIBLE
           boton_de_repuesto.visibility = View.VISIBLE



       }
    }

}