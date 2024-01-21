package com.example.registerforactivityresult

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.registerforactivityresult.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var pictureUri: Uri? = null
    private val getTakePicture = registerForActivityResult(
        ActivityResultContracts.TakePicture()) {
        if (it) {
            pictureUri.let { binding.image.setImageURI(pictureUri) }
        }
    }
    private val getTakePicturePreview = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap.let { binding.image.setImageBitmap(bitmap) }
    }
    private val getFileImage = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()) { uri ->
        uri.let { binding.image.setImageURI(uri) }
    }
    private companion object {
        private const val REQUEST_PERMISSIONS_CODE = 100
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.camera.setOnClickListener {
            val permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            val permissionList = arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_MEDIA_AUDIO,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_IMAGES
            )
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissionList, REQUEST_PERMISSIONS_CODE)
                ActivityCompat.OnRequestPermissionsResultCallback { requestCode, permissions, grantResults ->
                    when (requestCode) {
                        REQUEST_PERMISSIONS_CODE -> {
                            if (permissions[0] == Manifest.permission.CAMERA && grantResults.isNotEmpty()) {
                                pictureUri = createImageFile()
                                getTakePicture.launch(pictureUri) // Save
                                //getTakePicturePreview.launch(null) // Not Save
                            }
                        }
                    }
                }
            }
            if (permission == PackageManager.PERMISSION_GRANTED) {
                pictureUri = createImageFile()
                getTakePicture.launch(pictureUri)
                //getTakePicturePreview.launch(null)
            }
        }
        binding.file.setOnClickListener {
            getFileImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private fun createImageFile(): Uri? {
        val fileName = System.currentTimeMillis()
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "img_$fileName.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        }
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    }
}