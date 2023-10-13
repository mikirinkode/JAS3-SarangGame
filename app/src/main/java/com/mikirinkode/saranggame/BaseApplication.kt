package com.mikirinkode.saranggame

import android.app.Application
import com.mikirinkode.saranggame.data.AppContainer
import com.mikirinkode.saranggame.data.ContainerInterface

class BaseApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: ContainerInterface
    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}
