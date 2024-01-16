package com.example.alertdialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.alertdialog.databinding.ActivityMainBinding
import com.example.alertdialog.databinding.CustomDialogBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customDialogButton.setOnClickListener {
            val dlg = CustomDialog()
            dlg.setButtonClickListener(object : CustomDialog.OnButtonClickListener{
                override fun onButtonClicked() {
                    Toast.makeText(applicationContext, "확인 버튼 클릭됨", Toast.LENGTH_SHORT).show()
                }
            })
            dlg.show(supportFragmentManager, "CustomDialog")
        }
    }
}