package com.example.permission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private companion object {
        private const val REQUEST_PERMISSIONS_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermission()

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && PackageManager.PERMISSION_DENIED
//            == ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), REQUEST_PERMISSIONS_CODE)
//        }

        binding.single.setOnClickListener {
            val single = Manifest.permission.CAMERA
            requestSinglePermission.launch(single)
        }
        binding.multiple.setOnClickListener {
            val multiple = arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_AUDIO,
                Manifest.permission.READ_MEDIA_VIDEO
            )
            requestMultiplePermission.launch(multiple)
        }
    }

    private fun checkPermission() {
        val notificationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
        if (notificationPermission == PackageManager.PERMISSION_GRANTED) {
            notificationPermissionGranted()
        } else {
            requestPermission()
        }
    }

    private fun notificationPermissionGranted() {
        Toast.makeText(this, "알림 실행", Toast.LENGTH_SHORT).show()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), REQUEST_PERMISSIONS_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            100 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    notificationPermissionGranted()
                } else {
                    //finish()
                    Toast.makeText(this, "권한 허용 필요", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private val requestSinglePermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()) { isGranted ->
        Log.d("TAG", "singlePermissionLauncher: isGranted: $isGranted")

        if (isGranted) {
            singlePermissionGranted()
        } else {
            Log.d("TAG", "singlePermissionLauncher: 권한 거부")
            Toast.makeText(this, "권한 거부됨", Toast.LENGTH_SHORT).show()
        }
    }

    private fun singlePermissionGranted() {
        binding.tvLog.text = "단일 권한 부여됨"
    }

    private val requestMultiplePermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()) { results ->
//        var allGranted = true
//        for (isGranted in results.values) {
//            Log.d(TAG, "multiplePermissionLauncher: isGranted: $isGranted")
//            allGranted = allGranted && isGranted
//        }
//
//        if (allGranted) {
//            multiplePermissionGranted()
//        } else {
//            Log.d(TAG, "multiplePermissionLauncher: 권한 거부")
//            Toast.makeText(this, "권한 거부됨", Toast.LENGTH_SHORT).show()
//        }

        results.forEach {
            if(!it.value) {
                Toast.makeText(this, "${it.key} 권한 허용 필요", Toast.LENGTH_SHORT).show()
                //finish()
            } else {
                multiplePermissionGranted()
            }
        }
    }

    private fun multiplePermissionGranted() {
        binding.tvLog.text = "다중 권한 부여됨"
    }
}