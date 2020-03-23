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
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.shantanu.example.modelbasic.R
import com.shantanu.example.modelbasic.databinding.FragmentHomeBinding
import com.shantanu.example.modelbasic.databinding.FragmentLoginBinding
import com.shantanu.example.modelbasic.ui.feature.login.LoginFragment
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
            findNavController().navigate(R.id.fragment_login)
            findNavController().popBackStack()
            findNavController().navigateUp()

           // findNavController().popBackStack(R.id.nav_host,true)
            /*findNavController().navigateUp()
            if (!findNavController().popBackStack(R.id.nav_host,true)){

               // findNavController().currentDestination?.removeAction(R.id.action_home_to_login)
            }else{

            }*/
            //findNavController().popBackStack(R.id.nav_host,true)
           /* findNavController().popBackStack()
            if (!findNavController().popBackStack()) {
                activity?.finish()
            }*/



          //  findNavController().navigate(R.id.fragment_login)
           // findNavController().popBackStack()

        //findNavController().popBackStack(R.id.fragment_login,true)
        //findNavController().popBackStack(R.id.fragment_login, true)
    }



    return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() { // Handle the back button event
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        model.getUserInfo().observe(this, Observer {
            binding.fragmentHomeTextviewUserEmail.text="Hello "+it
        })


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

        binding.fragmentHomeTextviewDetail.text=model.getDetail()


    }



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
            //.setSound(sound)
            .setContentIntent(contentPendingIntent)
            .setAutoCancel(true)
            .setContentText("Hello! This is push notification")


        val mNotificationManager: NotificationManager =
            activity!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //intent


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