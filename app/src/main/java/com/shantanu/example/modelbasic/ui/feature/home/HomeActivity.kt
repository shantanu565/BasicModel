package com.shantanu.example.modelbasic.ui.feature.home

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.firebase.iid.FirebaseInstanceId
import com.shantanu.example.modelbasic.R
import com.shantanu.example.modelbasic.databinding.ActivityHomeBinding
import com.shantanu.example.modelbasic.ui.viewmodel.FirebaseBaseViewModel
import com.shantanu.example.modelbasic.utils.ConnectivityReceiver

class HomeActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    private var session: Boolean = false
    private lateinit var model: FirebaseBaseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        model = ViewModelProviders.of(this)[FirebaseBaseViewModel::class.java]
        var userSession=model.currentUser()
        Log.v("session",userSession.toString())


        //here
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment?
        navController = navHost!!.navController

        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.nav_graph_home)

        if (userSession?.email!=null) {
            graph.startDestination = R.id.fragment_home
        } else {
            graph.startDestination = R.id.fragment_login
        }

        navController.graph = graph

        var actionBar: ActionBar? =supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        NavigationUI.setupActionBarWithNavController(this, navController)

        val options = NavOptions.Builder()
            .setPopUpTo(R.id.fragment_login, true)
            .build()

        //here


       /* navController = Navigation.findNavController(this, R.id.nav_host)

        // var actionBar: ActionBar? =supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        NavigationUI.setupActionBarWithNavController(this, navController)

*/
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    override fun onBackPressed() {
        super.onBackPressed()

       // navController.popBackStack()
    }


    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)

    }

    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Internet Connected", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
        onNewToken()
    }

    //here
    fun onNewToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    //Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                Log.v("token",token)

            })
    }

}
