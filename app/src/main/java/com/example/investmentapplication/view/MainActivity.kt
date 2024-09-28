package com.example.investmentapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.investmentapplication.R
import com.example.investmentapplication.temp.DatabaseExecution
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : ComponentActivity() {

    val CAMERA_REQUEST_CODE = 0
    var imageView: ImageView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val executor = DatabaseExecution()
        executor.execute(this)

        // setupActionBarWithNavController(findNavController(R.id.fragment))
        var buttonAdd: Button =
            findViewById(R.id.button_add)
        imageView =
            findViewById<ImageView>(R.id.image_view_profile)!!

        buttonAdd.setOnClickListener {
            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (callCameraIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (resultCode == RESULT_OK && data != null) {
                    val bitmap = data.extras?.get("data") as Bitmap
                    val fileName = saveToInternalStorage(this, bitmap)
                    Log.e("inside", "-->" + fileName)
                    val executor = DatabaseExecution()
                    executor.execute(this)
                    executor.insertTestUser(
                        "first last",
                        "9988776655",
                        fileName
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        delay(1000)
                        val result = executor.userList
                        Log.e("inside", "->" + result.size)
                        withContext(Dispatchers.Main) {
                            if (result.isNotEmpty()) {
                                showFile(result.get(result.size - 1).path)
                            }
                        }
                    }
                }
            }

            else -> {
                Toast.makeText(this, "Unrecognized request code", Toast.LENGTH_SHORT)
            }
        }
    }

    @Throws(IOException::class)
    private fun saveToInternalStorage(context: Context, bitmapImage: Bitmap): String {
        val cw = ContextWrapper(context)

        // Path to /data/data/yourapp/app_data/imageDir
        val directory = cw.getDir("image_dir", MODE_PRIVATE)

        val fileName = "image_" + System.currentTimeMillis() + ".jpg"
        // Create imageDir
        val mypath = File(directory, fileName)

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(mypath)
            // Use the compress method on the BitMap object to write image to the OutputStream
            //Bitmap scaledBitmap = getCompressedBitmap(bitmapImage);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 70, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fos!!.close()
        }
        return directory.absolutePath + "/" + fileName
    }

    fun showFile(fileName: String) {
        val imgFile = File(fileName)

        if (imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)

            imageView?.setImageBitmap(myBitmap)
        }
    }
}