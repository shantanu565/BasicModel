package com.shantanu.example.modelbasic.ui.feature.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.shantanu.example.modelbasic.R
import com.shantanu.example.modelbasic.network.firebase.CustomFirebaseMessagingService

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

       /* val buttonSend=findViewById<Button>(R.id.btn_send)
        buttonSend.setOnClickListener {
            Log.d("l","token")
            var token=FirebaseInstanceId.getInstance().instanceId
            Log.v("token",token.toString())
            Toast.makeText(this,token.toString(),Toast.LENGTH_LONG).show()

            val firebaseMessagingService=FirebaseMessagingService(this)

        }*/





    }
}
