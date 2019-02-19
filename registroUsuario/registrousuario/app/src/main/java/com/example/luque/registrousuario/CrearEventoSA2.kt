package com.example.luque.registrousuario

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_crear_evento_sa2.*

class CrearEventoSA2 : AppCompatActivity() {

    private val PERMISSION_CODE: Int = 1000
    var image_rui: Uri? = null
    val IMAGE_CAPTURE_CODE: Int = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_evento_sa2)

        val registerSede = intent.getStringExtra("registerSede")
        val registerEvento = intent.getStringExtra("registerEvento")


        agregar_imagen.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED || checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    //permiso denegado
                    val permission = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

                    requestPermissions(permission, PERMISSION_CODE)

                } else {

                    val popupWindow = PopupWindow(this)

                    popupWindow.showAtLocation(boton_de_repuesto, Gravity.AXIS_PULL_AFTER, 50, 50)


                    val dialog: DialogInterface? = null
                    val wich: Int = 0


                    val selectorDeImagen = AlertDialog.Builder(this)
                    selectorDeImagen.setTitle("Elige que hacer")

                    val itemsDeImage: Array<String> = arrayOf("Tomar foto con camara", "Seleccionar foto desde la galeria")


                    val popupMenu = PopupMenu(this, it)

                    popupMenu.setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.abrir_camara -> {
                                abrirCamara()
                                true

                            }

                            R.id.abrir_galeria -> {
                                abrirGaleria()
                                true
                            }
                            else -> false
                        }
                    }
                    //popupMenu.inflate(R.menu.popup_de_camara)
                    popupMenu.show()
                }

            } else {

                val popupWindow = PopupWindow(this)

                popupWindow.showAtLocation(boton_de_repuesto, Gravity.CENTER, 50, 50)

                val popupMenu = PopupMenu(this, it)
                popupMenu.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.abrir_camara -> {
                            abrirCamara()
                            true

                        }

                        R.id.abrir_galeria -> {
                            abrirGaleria()
                            true
                        }
                        else -> false
                    }

                    }
                    popupMenu.inflate(R.menu.popup_de_camara)
                    popupMenu.show()
                }

            }


            button_next2.setOnClickListener {
                val intent = Intent(applicationContext, CrearEventoSA3::class.java)
                intent.putExtra("registerSede", registerSede)
                intent.putExtra("registerEvento", registerEvento)
                startActivity(intent)
            }

            boton_de_repuesto.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED || checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                        //permiso denegado
                        val permission = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

                        requestPermissions(permission, PERMISSION_CODE)

                    } else {
                        val popupMenu = PopupMenu(this, it)
                        popupMenu.setOnMenuItemClickListener { item ->
                            when (item.itemId) {
                            R.id.abrir_camara -> {
                                abrirCamara()
                                true

                            }

                            R.id.abrir_galeria -> {
                                abrirGaleria()
                                true
                            }
                            else -> false
                        }
                            }
                            //popupMenu.inflate(R.menu.popup_de_camara)
                            //popupMenu.show()
                        }

                    }

            else{
                val popupMenu = PopupMenu(this, it)
                popupMenu.setOnMenuItemClickListener { item ->
                    when (item.itemId){
                        R.id.abrir_camara -> {
                            abrirCamara()
                            true

                        }

                        R.id.abrir_galeria -> {
                            abrirGaleria()
                            true
                        }
                        else -> false
                    }
                }
                popupMenu.inflate(R.menu.popup_de_camara)
                popupMenu.show()

            }
                }

            }

            fun abrirCamara() {

                val valores = ContentValues()

                valores.put(MediaStore.Images.Media.TITLE, "New picture")
                valores.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")

                image_rui = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, valores)

                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_rui)
                startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)


            }

            fun abrirGaleria() {

                Toast.makeText(this, "Prro", Toast.LENGTH_SHORT).show()

            }

            override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

                when (requestCode) {
                    PERMISSION_CODE -> {
                        if (grantResults.size == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                            abrirCamara()

                        }

                    }
                }

            }


            override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                if (resultCode == Activity.RESULT_OK) {

                    agregar_imagen.visibility = View.INVISIBLE
                    imagen_de_boton.setImageURI(image_rui)
                    imagen_de_boton.visibility = View.VISIBLE
                    boton_de_repuesto.visibility = View.VISIBLE
                }
            }
        }




