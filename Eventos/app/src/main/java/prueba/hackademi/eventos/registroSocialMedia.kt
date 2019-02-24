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

    open var googleApiClient: GoogleApiClient? = null
    open var signInButton: SignInButton? = null
    var SIGN_IN_CODE = 777


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_social_media)
        //loginButton?.setReadPermissions("email")

        var correo = findViewById<Button>(R.id.loginCorreo)
        correo.setOnClickListener {
            var intent100 = Intent(this, registroManual::class.java)
            startActivity(intent100)
        }
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
    }

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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
        handleSignInResult(result)
    }


}






