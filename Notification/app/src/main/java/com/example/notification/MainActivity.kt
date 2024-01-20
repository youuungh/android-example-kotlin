package com.example.notification

import android.Manifest
import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import com.example.notification.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var picker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var setting: SharedPreferences

    companion object {
        const val REQUEST_CODE = 101
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        TedPermission.create().setPermissionListener(object : PermissionListener {
            override fun onPermissionGranted() {
                Toast.makeText(this@MainActivity, "Permission Granted", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        })
            .setDeniedMessage("알림 권한을 승인해주세요")
            .setPermissions(POST_NOTIFICATIONS)
            .check()

        setting = getSharedPreferences("setting", MODE_PRIVATE)
        binding.mSwitch.isChecked = setting.getBoolean("alarm", false)

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val pendingIntent = Intent(this, AlarmReceiver::class.java).let {
            PendingIntent.getBroadcast(this, REQUEST_CODE, it, PendingIntent.FLAG_IMMUTABLE)
        }

        binding.setTime.setOnClickListener {
            showTimePicker()
        }

        binding.mSwitch.setOnCheckedChangeListener { _, isCheck ->
            setting.edit {
                putBoolean("alarm", isCheck)
            }
            if(isCheck) {
                alarmManager.set(
                    //AlarmManager.ELAPSED_REALTIME_WAKEUP
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
                appendLog("알람 시작")
            } else {
                alarmManager.cancel(pendingIntent)
                appendLog("알람 취소")
            }
        }
    }

    private fun showTimePicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("시간을 선택하세요")
            .build()
        picker.show(supportFragmentManager, "time")
        picker.addOnPositiveButtonClickListener {
            if (picker.hour > 12) {
                binding.tvTime.text = String.format("%02d", picker.hour - 12) + " : " + String.format("%02d", picker.minute) + " PM"
            } else {
                binding.tvTime.text = String.format("%02d", picker.hour) + " : " + String.format("%02d", picker.minute) + " AM"
            }
            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0
        }
    }

    private fun appendLog(str: String) {
        binding.tvLog.text = "${binding.tvLog.text}\n${str}"
    }
}