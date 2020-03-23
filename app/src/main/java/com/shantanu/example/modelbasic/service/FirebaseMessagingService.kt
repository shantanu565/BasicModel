package com.shantanu.example.modelbasic.service

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        Log.d("msg", "From: ${remoteMessage?.from}")
        remoteMessage?.notification?.let {
            Log.d("msg", "Message Notification Body: ${it.body}")
            // Check if message contains a notification payload.
            remoteMessage?.notification?.let {
                Log.d("msg", "Message Notification Body: ${it.body}")
                //Message Services handle notification
                /*    val notification = NotificationCompat.Builder(context)
                     .setContentTitle(remoteMessage.from)
                     .setContentText(it.body)
                     .setSmallIcon(R.mipmap.ic_launcher)
                     .build()
                 val manager = NotificationManagerCompat.from(context)
                 manager.notify(0, notification)*/


                //  }
            }
        }
    }


    override fun onNewToken(token: String) {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                Log.v("token", token)

            })
    }

}

