package com.example.luque.registrousuario

import android.app.Application
import com.facebook.FacebookSdk

import com.facebook.appevents.AppEventsLogger

class facebookApp : Application()  {

    override fun onCreate() {
        super.onCreate()

        AppEventsLogger.activateApp(this)
        FacebookSdk.sdkInitialize(applicationContext)
    }
}
