package com.example.luque.registrousuario

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import android.content.Intent
import android.view.View
import com.facebook.login.LoginManager
import com.facebook.login.widget.LoginButton
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.appevents.AppEventsLogger
import com.facebook.appevents.AppEventsLogger.*


//import sun.security.krb5.internal.KDCOptions.with

class registroSocialMedia : AppCompatActivity()
{

    //private var profileTracker: ProfileTracker? = null
    //private var photoImageView: ImageView? = null
    //private var nameTextView: TextView? = null
    //private var idTextView: TextView? = null

    //otra pantalla LOGIN
    var loginButton: LoginButton? = null
    var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registro_social_media)

        //photoImageView = findViewById(R.id.photoImageView) as ImageView
        //nameTextView = findViewById(R.id.nameTextView) as TextView
        //idTextView = findViewById(R.id.idTextView) as TextView

        //otra pantalla LOGIN
        callbackManager = CallbackManager.Factory.create()
        loginButton = findViewById(R.id.botonlogin)
        //loginButton?.setReadPermissions("email")

        /*
        profileTracker = object : ProfileTracker()
        {
                override fun onCurrentProfileChanged(oldProfile: Profile, currentProfile: Profile?)
                {
                    if (currentProfile != null)
                    {
                        displayProfileInfo(currentProfile)
                    }
                }
        }
        if (AccessToken.getCurrentAccessToken() == null)
        {
            goLoginScreen()
        }
        else
        {
            val profile = Profile.getCurrentProfile()
            if (profile != null)
            {
                displayProfileInfo(profile)
            }
            else
            {
                Profile.fetchProfileForCurrentAccessToken()
            }
        }*/
        loginButton?.registerCallback(callbackManager, object : FacebookCallback<LoginResult>{
            override fun onSuccess(loginResult: LoginResult) {
                goMainScreen()
            }

            override fun onCancel() {
                Toast.makeText(applicationContext, "R.string.cancel_login", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: FacebookException) {
                Toast.makeText(applicationContext, "R.string.error_login", Toast.LENGTH_SHORT).show()
            }
        })
    }
    /*
    private fun displayProfileInfo(profile: Profile)
    {
        //val id = profile.id
        val name = profile.firstName as String
        //val photoUrl = profile.getProfilePictureUri(100, 100).toString()

        nameTextView?.text = name
        idTextView?.text = id

        Glide.with(this.applicationContext)
                .load(photoUrl)
                .into(photoImageView)
    }
    */


    private fun goLoginScreen()
    {
        val intent = Intent(this, registroSocialMedia::class.java)

        startActivity(intent)
    }

    fun logout(view: View)
    {
        LoginManager.getInstance().logOut()
        goLoginScreen()
    }
    /*
    override fun onDestroy()
    {
        super.onDestroy()
        profileTracker?.stopTracking()
    }
    */

    //LOGIN ACTIVITE

    private fun goMainScreen() {
        val intent = Intent(this, datosRegistro::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }
}

