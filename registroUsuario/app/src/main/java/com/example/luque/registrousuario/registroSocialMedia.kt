@file:Suppress("DEPRECATION")

package com.example.luque.registrousuario

//import android.content.Intent
//import android.view.View
//import android.widget.Toast

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.bumptech.glide.Glide
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
import com.google.android.gms.common.api.Result
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterLoginButton



import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthToken
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession

import retrofit2.Response.success


//import sun.security.krb5.internal.KDCOptions.with

class registroSocialMedia : AppCompatActivity(), OnConnectionFailedListener {

    private var profileTracker: ProfileTracker? = null
    //private var photoImageView: ImageView? = null
    //private var nameTextView: TextView? = null
    //private var idTextView: TextView? = null

    //otra pantalla LOGIN
    var loginButton: LoginButton? = null
    var callbackManager: CallbackManager? = null

    //REGISTRO CON GOOGLE
    var googleApiClient: GoogleApiClient? = null

    var signInButton: SignInButton? = null

    var SIGN_IN_CODE = 777

    //LOGIN TWITTER
    var loginButtonTwitter : TwitterLoginButton? = null
    var callback : Callback<TwitterSession>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Twitter.initialize(this);
        setContentView(R.layout.activity_registro_social_media)

        //photoImageView = findViewById(R.id.photoImageView) as ImageView
        //nameTextView = findViewById(R.id.nameTextView) as TextView
        //idTextView = findViewById(R.id.idTextView) as TextView

        //LOGIN TWITTER
        loginButtonTwitter.setCallback(new callback Callback<TwitterSession>() {
            @Override
             fun success(Result<TwitterSession> result) {
                /*
                  This provides TwitterSession as a result
                  This will execute when the authentication is successful
                 */
                var session : TwitterSession  = TwitterCore.getInstance().getSessionManager().getActiveSession()
                var authToken : TwitterAuthToken  = session.getAuthToken()
                var token : String = authToken.token
                var secret : String = authToken.secret

                //Calling login method and passing twitter session
                login(session)
                )}

            @Override
            fun failure(TwitterException exception) {
                //Displaying Toast message
                Toast.makeText(this, "Authentication failed!", Toast.LENGTH_LONG).show()
            }
        })






        //otra pantalla LOGIN
        callbackManager = CallbackManager.Factory.create()
        loginButton = findViewById(R.id.botonlogin)
        loginButton?.setReadPermissions("email")

        var correo = findViewById(R.id.loginCorreo) as Button
        correo.setOnClickListener{
            var intent7 = Intent(this, registroManual::class.java)
            startActivity(intent7)
        }



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
        }
        loginButton?.registerCallback(callbackManager, object : FacebookCallback<LoginResult>{
            override fun onSuccess(loginResult: LoginResult) {
                goMainScreen()
                Toast.makeText(applicationContext, "inicio correcto", Toast.LENGTH_SHORT).show()
            }

            override fun onCancel() {
                Toast.makeText(applicationContext, "R.string.cancel_login", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: FacebookException) {
                Toast.makeText(applicationContext, "R.string.error_login", Toast.LENGTH_SHORT).show()
            }
        })


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        googleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        signInButton = findViewById(R.id.signInButton) as SignInButton

        signInButton?.setSize(SignInButton.SIZE_WIDE)

        signInButton?.setColorScheme(SignInButton.COLOR_DARK)

        signInButton?.setOnClickListener{
            //val intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
            //startActivityForResult(intent, 777)
        }
    }

    private fun displayProfileInfo(profile: Profile)
    {
        val id = profile.id
        val name = profile.firstName as String
        val photoUrl = profile.getProfilePictureUri(100, 100).toString()

        //nameTextView?.text = name
        //idTextView?.text = id

        Glide.with(this.applicationContext)
                //.load(photoUrl)
                //.into(photoImageView)
    }



    private fun goLoginScreen()
    {
        //val intent = Intent(this, login::class.java) //checar

        //startActivity(intent) //checar
    }

    fun logout(view: View)
    {
        LoginManager.getInstance().logOut()
        goLoginScreen()
    }

    override fun onDestroy()
    {
        super.onDestroy()
        profileTracker?.stopTracking()
    }


    //LOGIN ACTIVITE

    override fun onConnectionFailed(connectionResult: ConnectionResult) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 777) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess()) {
            goMainScreen()
        } else {
            Toast.makeText(this, "R.string.not_log_in", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goMainScreen() {
       // val intent = Intent(this, datosRegistro::class.java) //checar
       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK) //checar
       // startActivity(intent) //checar
    }

    //LOGIN TWITTER
    fun login(session: TwitterSession) {
        val username = session.userName
        val intent = Intent(this@registroSocialMedia, registroSocialMedia::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
    }
    fun onActivityResultTwitter(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result to the login button.
        loginButtonTwitter?.onActivityResult(requestCode, resultCode, data)
    }
}

