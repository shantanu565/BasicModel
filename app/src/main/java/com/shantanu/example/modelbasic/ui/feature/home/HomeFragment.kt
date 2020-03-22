package com.shantanu.example.modelbasic.ui.feature.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.shantanu.example.modelbasic.R
import com.shantanu.example.modelbasic.databinding.FragmentHomeBinding
import com.shantanu.example.modelbasic.databinding.FragmentLoginBinding
import com.shantanu.example.modelbasic.ui.feature.login.LoginViewModel
import com.shantanu.example.modelbasic.ui.viewmodel.FirebaseBaseViewModel

class HomeFragment : Fragment() {
    private lateinit var model: FirebaseBaseViewModel
    private lateinit var binding: FragmentHomeBinding
    private val NOTIFICATION_CHANNEL_ID = "10001"
    private val default_notification_channel_id = "default"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false


        )
        model = ViewModelProviders.of(this)[FirebaseBaseViewModel::class.java]

        binding.fragmentHomeButtonLocalNotification.setOnClickListener {
            sendNotification()

        }

        binding.fragmentHomeButtonLogout.setOnClickListener {
            model.logout()
            //findNavController().navigate(R.id.fragment_login)
            findNavController().popBackStack(R.id.fragment_login, true)
        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        //for noti
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("token", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                val msg = token
                Log.v("fb", msg)
                Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
            })


    }

    private fun sendNotification() {
        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(
            activity?.applicationContext!!,
            default_notification_channel_id
        )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("New Notify")
            //.setSound(sound)
            .setContentText("Hello! This is push notification");
        val mNotificationManager: NotificationManager =
            activity!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            val importance: Int = NotificationManager.IMPORTANCE_HIGH;
            val notificationChannel: NotificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "NOTIFICATION_CHANNEL_NAME",
                importance
            )
            notificationChannel.enableLights(true)
            notificationChannel.setLightColor(Color.RED)
            notificationChannel.enableVibration(true)
            //notificationChannel.setVibrationPattern( Long []{ 100 , 200 , 300 , 400 , 500 , 400 , 300 , 200 , 400 }) ;
            //notificationChannel.setSound(sound , audioAttributes) ;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)

            mNotificationManager.createNotificationChannel(notificationChannel)
        }

        mNotificationManager.notify(0, mBuilder.build())
    }


}