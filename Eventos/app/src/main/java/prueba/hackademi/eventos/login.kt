package prueba.hackademi.eventos

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
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
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //var loginButton: LoginButton? = null
    //var callbackManager: CallbackManager? = null

    //private var loginButtonTwitter : TwitterLoginButton? = null
    //var callback : Callback<TwitterSession>? = null

    open var googleApiClient: GoogleApiClient? = null
    open var signInButton: SignInButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Twitter.initialize(this)
        setContentView(R.layout.activity_login)
        //callbackManager = CallbackManager.Factory.create()

      //  loginButtonTwitter = findViewById(R.id.loginButtonTwitterr)
        val textoContraseña= findViewById(R.id.textoContraseña) as TextView
        var valor = true
        botonOjo.setOnClickListener{
            if(valor == true){
                textoContraseña.setInputType(InputType.TYPE_CLASS_TEXT);
                botonOjo.setBackgroundResource(R.drawable.ojocontra)
                valor=false
            }
            else{
                textoContraseña.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                botonOjo.setBackgroundResource(R.drawable.ojocontradesac2)
                valor=true
            }
        }

        val botonlogin= findViewById(R.id.botonlogin) as Button
        botonlogin.setOnClickListener{

           // val intent3 = Intent(applicationContext,MainActivity::class.java)
            val intent3 = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent3)
            finishAffinity()
        }



        /*
        loginButton?.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
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
        */
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        googleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        signInButton = this.findViewById(R.id.SignInButton)

        signInButton?.setSize(com.google.android.gms.common.SignInButton.SIZE_WIDE)

        signInButton?.setColorScheme(com.google.android.gms.common.SignInButton.COLOR_DARK)


        signInButton?.setOnClickListener {
            val intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
            startActivityForResult(intent, 777)
        }

        //var loginButtonTwitter = findViewById<TwitterLoginButton>(R.id.loginButtonTwitterr) //BIEN
        /*
        loginButtonTwitter?.callback = object :Callback<TwitterSession>(){
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
            //BIEN
        }
        */


    }

    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }
    */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //if (requestCode == 777) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
       // }
        /*
        if(requestCode == 64206){
            callbackManager?.onActivityResult(requestCode, resultCode, data)
        }
        if(requestCode == 140){
            loginButtonTwitter?.onActivityResult(requestCode, resultCode, data)
        }
        */
    }


    private fun goMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    private fun handleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
            goMainScreen()
        } else {
            Toast.makeText(this, "R.string.not_log_in", Toast.LENGTH_SHORT).show()
        }
    }   /*
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
    */
}
