package prueba.hackademi.eventos
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
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterLoginButton

open class registroSocialMedia :  AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
//, GoogleApiClient.OnConnectionFailedListener

    private var profileTracker: ProfileTracker? = null
    //otra pantalla LOGIN
    var loginButton: LoginButton? = null
    var callbackManager: CallbackManager? = null
    //REGISTRO CON GOOGLE
    open var googleApiClient: GoogleApiClient? = null
    open var signInButton: SignInButton? = null
    var SIGN_IN_CODE = 777
    //LOGIN TWITTER
    var loginButtonTwitter : TwitterLoginButton? = null
    var callback : Callback<TwitterSession>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Twitter.initialize(this) // twitter
        setContentView(R.layout.activity_registro_social_media)
        loginButtonTwitter = findViewById(R.id.loginButtonTwitterr)
        //otra pantalla LOGIN facebook
        callbackManager = CallbackManager.Factory.create()
        loginButton = findViewById(R.id.botonlogin)
        //loginButton?.setReadPermissions("email")

        var correo = findViewById<Button>(R.id.loginCorreo)
        correo.setOnClickListener {
            var intent100 = Intent(this, registroManual::class.java)
            startActivity(intent100)
        }
        /*
        profileTracker = object : ProfileTracker() {
            override fun onCurrentProfileChanged(oldProfile: Profile, currentProfile: Profile?) {
                if (currentProfile != null) {
                    displayProfileInfo(currentProfile)
                }
            }
        }
        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen()
        } else {
            val profile = Profile.getCurrentProfile()
            if (profile != null) {
                displayProfileInfo(profile)
            } else {
                Profile.fetchProfileForCurrentAccessToken()
            }
        }
        */
        loginButton?.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                goMainScreen()
                Toast.makeText(applicationContext, "inicio correcto", Toast.LENGTH_SHORT).show()
            }

            override fun onCancel() {
                Toast.makeText(applicationContext, "R.string.cancel_login", Toast.LENGTH_SHORT).show()
                print("entre cancel")
            }

            override fun onError(error: FacebookException) {
                Toast.makeText(applicationContext, "R.string.error_login", Toast.LENGTH_SHORT).show()
                print("entre error")
            }
        })

        //GOOGLE

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        googleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        signInButton = findViewById(R.id.SignInButton)

        signInButton?.setSize(SignInButton.SIZE_WIDE)

        signInButton?.setColorScheme(SignInButton.COLOR_DARK)

        signInButton?.setOnClickListener {
            val intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
            startActivityForResult(intent, 777)
        }


        //TWITTER
        var loginButtonTwitter = findViewById<TwitterLoginButton>(R.id.loginButtonTwitterr) //BIEN
        loginButtonTwitter.callback = object :Callback<TwitterSession>(){
            override fun success(Result : Result<TwitterSession>) {
                var session : TwitterSession = TwitterCore.getInstance().sessionManager.activeSession
                var authToken: TwitterAuthToken = session.authToken
                var token = authToken.token
                val secret = authToken.secret
                login(session)

            }
            override fun failure(exception: TwitterException) {
                //Toast.makeText(this, "Authentication failed!", Toast.LENGTH_LONG).show()
            }
        }
    }

    //FACEBOOK
    /*
    private fun displayProfileInfo(profile: Profile)
    {
        val id = profile.id
        val name = profile.firstName as String
        val photoUrl = profile.getProfilePictureUri(100, 100).toString()

        nameTextView?.text = name
        idTextView?.text = id

        Glide.with(this.applicationContext)
                .load(photoUrl)
                .into(photoImageView)
    }
    */
    private fun goLoginScreen()
    {
        val intent = Intent(this, datosRegistro::class.java) //checar
        startActivity(intent) //checar
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

    //LOGIN ACTIVITE GOOGLE

    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {//da error por el metodo de TWITTER que tiene el mismo nombre con los mismos parametros si el metodo de Twitter es comentariado agarra el de google,el metodo esta bien implementado
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 777) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
        if(requestCode == 64206){
            callbackManager?.onActivityResult(requestCode, resultCode, data)
        }
        if(requestCode == 140){
            loginButtonTwitter?.onActivityResult(requestCode, resultCode, data)
        }
    }
    */


    override fun onConnectionFailed(connectionResult: ConnectionResult) {

    }
    private fun handleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
            goMainScreen()
        } else {
            Toast.makeText(this, "R.string.not_log_in", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goMainScreen() {
       val intent = Intent(this, datosRegistro::class.java) //checar
       //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK) //checar
       startActivity(intent) //checar
    }


    //LOGIN TWITTER

    fun login(session: TwitterSession) {
        val username = session.userName
        Toast.makeText(this, "Authentication suceesfult!", Toast.LENGTH_LONG).show()
        val intent10 = Intent(
                this,
                datosRegistro::class.java
        )
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("username", username)
        startActivity(intent10)
    }

/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //da error por el metodo de google que tiene el mismo nombre con los mismos parametros si los metodos de google son comentariados agarra el de twitter,el metodo esta bien implementado
        super.onActivityResult(requestCode, resultCode, data)
        loginButtonTwitter?.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(this, "el valor de requestcode es: "+requestCode, Toast.LENGTH_SHORT).show()
    }
    */


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
        handleSignInResult(result)
        //callbackManager?.onActivityResult(requestCode, resultCode, data)
       //Toast.makeText(this, "el valor de requestcode es: "+requestCode, Toast.LENGTH_SHORT).show()

    }


}






