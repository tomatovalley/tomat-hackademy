package prueba.hackademi.eventos

import android.app.Application
import com.facebook.FacebookSdk.sdkInitialize
import com.facebook.appevents.AppEventsLogger

class facebookApp : Application()  {

    override fun onCreate() {
        super.onCreate()

        AppEventsLogger.activateApp(this)
        sdkInitialize(applicationContext)
    }
}
