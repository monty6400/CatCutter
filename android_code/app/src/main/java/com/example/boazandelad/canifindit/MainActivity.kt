package com.example.boazandelad.canifindit

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.InetAddress
import java.net.Socket
import java.net.UnknownHostException

class MainActivity : AppCompatActivity() {

    private var socket: Socket? = null
    private var imageView:ImageView? = null
    companion object {
        const val PICK_IMAGE:Int = 100
        val SERVERPORT = 5000
        val SERVER_IP = "10.0.0.2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.pic_view)

        Thread(ClientThread()).start();
    }

    fun openGallery(view: View){
        var gallery = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE){
            var imageUri = data?.data
            imageView?.setImageURI(imageUri)
        }
    }

    fun sendImage (view: View){
        try {
            var bmp = (imageView?.drawable as BitmapDrawable).bitmap
            var bos = ByteArrayOutputStream()
            bmp.compress(Bitmap.CompressFormat.JPEG, 0, bos)
            var barrray = bos.toByteArray()

            

        }
    }

    inner class ClientThread: Runnable {
        override fun run() {
            try{
                var serverAddress = InetAddress.getByName(SERVER_IP)

                socket= Socket(serverAddress, SERVERPORT)
            } catch (e: UnknownHostException){
                e.printStackTrace()
            } catch (e: IOException){
                e.printStackTrace()
            }
        }
    }
}
