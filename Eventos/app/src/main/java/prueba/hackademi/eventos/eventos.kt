@file:Suppress("DEPRECATION")

package prueba.hackademi.eventos

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.facebook.login.LoginManager




class eventos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos)
        var btnLogout = findViewById(R.id.btnLogout) as Button

        btnLogout.setOnClickListener{
            LoginManager.getInstance().logOut()
        }
        val username = intent.getStringExtra("username")
        val uname = findViewById(R.id.TV_username) as TextView
        uname.setText(username)



    }

}
