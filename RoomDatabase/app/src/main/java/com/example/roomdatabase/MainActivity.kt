package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import androidx.core.text.isDigitsOnly
import com.example.db.FoodDatabase
import com.example.model.Food
import com.example.roomdatabase.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var db: FoodDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FoodDatabase.getInstance(this)

        binding.insertButton.setOnClickListener {
            insert()
            binding.root.hideKeyboard()
        }
        binding.deleteButton.setOnClickListener {
            delete()
            binding.root.hideKeyboard()
        }
        binding.dataUpdate.setOnClickListener {
            dataUpdate()
            binding.root.hideKeyboard()
        }
    }

    private fun insert() {
        val name = if (binding.insertName.text.isBlank()) "Empty" else binding.insertName.text.toString()
        val category = if (binding.insertCategory.text.isBlank()) "Empty" else binding.insertCategory.text.toString()
        val price = if (binding.insertPrice.text.isBlank()) 0 else binding.insertPrice.text.toString().toInt()

        CoroutineScope(Dispatchers.IO).launch {
            db?.DAO()?.insertFood(
                Food(name, category, price)
            )
        }
        Snackbar.make(binding.root, "삽입됨", Snackbar.LENGTH_SHORT).show()
        binding.insertName.setText("")
        binding.insertCategory.setText("")
        binding.insertPrice.setText("")
    }

    fun update(view: View) {
        view.hideKeyboard()
        val id = binding.updateID.text.toString().toLong()
        val value = binding.updateValue.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            when(view.id) {
                R.id.updateName -> db?.DAO()?.updateName(id, value)
                R.id.updateCategory -> db?.DAO()?.updateCategory(id, value)
                R.id.updatePrice -> {
                    if (value.isDigitsOnly())
                        db?.DAO()?.updatePrice(id, value.toInt())
                    else
                        db?.DAO()?.updatePrice(id, 0)
                }
            }
        }
    }

    private fun delete() {
        val id = if (binding.deleteID.text.isBlank()) 0L else binding.deleteID.text.toString().toLong()

        CoroutineScope(Dispatchers.IO).launch {
            val check = db?.DAO()?.isFood(id)

            CoroutineScope(Dispatchers.Main).launch {
                if (check == 1)
                    Snackbar.make(binding.root, "삭제됨", Snackbar.LENGTH_SHORT).show()
                else
                    Snackbar.make(binding.root, "ID를 찾을 수 없음", Snackbar.LENGTH_SHORT).show()
            }
            db?.DAO()?.deleteFood(id)
        }
        binding.deleteID.setText("")
    }

    private fun dataUpdate() {
        CoroutineScope(Dispatchers.IO).launch {
            val count = db?.DAO()?.getCount()
            val list = db?.DAO()?.getAll()

            CoroutineScope(Dispatchers.Main).launch {
                if (count == 0) {
                    binding.dataView.text = "NoData"
                } else {
                    binding.dataView.text = list?.joinToString("\n")
                }
            }
        }
    }

    private fun View.hideKeyboard() {
        (context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(windowToken, HIDE_NOT_ALWAYS)
    }

    override fun onDestroy() {
        super.onDestroy()
        db = null
        FoodDatabase.deleteInstance()
    }
}