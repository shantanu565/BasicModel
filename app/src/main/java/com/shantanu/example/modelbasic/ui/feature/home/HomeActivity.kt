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
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.firebase.iid.FirebaseInstanceId
import com.shantanu.example.modelbasic.R
import com.shantanu.example.modelbasic.databinding.ActivityHomeBinding
import com.shantanu.example.modelbasic.network.firebase.CustomFirebaseMessagingService
import com.shantanu.example.modelbasic.utils.ConnectivityReceiver

class HomeActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    private var session: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        navController = Navigation.findNavController(this, R.id.nav_host)


        // var actionBar: ActionBar? =supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        NavigationUI.setupActionBarWithNavController(this, navController)


        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )


        //manual session demo
        //TODO firebase session checking
        if (session) {
            navController.navigate(R.id.fragment_home)
        } else {

            navController.navigate(R.id.fragment_login)
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        navController.popBackStack()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                AuthUI.getInstance().signOut(applicationContext!!)
                navController.navigate(R.id.fragment_login)
                Toast.makeText(applicationContext, "Logout", Toast.LENGTH_LONG).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
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
    }

}
