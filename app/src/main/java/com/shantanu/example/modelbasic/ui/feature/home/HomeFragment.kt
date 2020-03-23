package com.shantanu.example.modelbasic.ui.feature.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.shantanu.example.modelbasic.R
import com.shantanu.example.modelbasic.databinding.FragmentHomeBinding
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
            //logout user
            model.logout()
            findNavController().navigate(R.id.fragment_login)
            findNavController().popBackStack()
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //obtaining loggedin user email id for welcome message
        model.getUserInfo().observe(this, Observer {
            binding.fragmentHomeTextviewUserEmail.text = "Hello " + it
        })


        //for notification token id
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
                //Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
            })

        //setting firebase welcome details in textview
        binding.fragmentHomeTextviewDetail.text = model.getDetail()


    }


    //creating & sending notification
    private fun sendNotification() {

        val contentIntent = Intent(activity?.applicationContext, HomeActivity::class.java)
        val contentPendingIntent = PendingIntent.getActivity(
            activity?.applicationContext,
            10001,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(
            activity?.applicationContext!!,
            default_notification_channel_id
        )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("New Notify")
            .setContentIntent(contentPendingIntent)
            .setAutoCancel(true)
            .setContentText("Hello! This is push notification")


        val mNotificationManager: NotificationManager =
            activity!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

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
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)

            mNotificationManager.createNotificationChannel(notificationChannel)
        }

        mNotificationManager.notify(0, mBuilder.build())
    }


}