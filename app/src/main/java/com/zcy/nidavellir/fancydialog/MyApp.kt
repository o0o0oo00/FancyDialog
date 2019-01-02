package com.zcy.nidavellir.fancydialog

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.SoftReference

/**
 * @author:         zhaochunyu
 * @description:    ${DESP}
 * @date:           2019/1/2
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        this.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {

            }

            override fun onActivityResumed(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity) {
                topActivity = SoftReference(activity)
            }

            override fun onActivityDestroyed(activity: Activity?) {
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            }

        })
    }

    companion object {
        lateinit var instance: Application
        lateinit var topActivity: SoftReference<Activity>

    }
}