package com.suro.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.suro.oop2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.tugastBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, TugasActivity::class.java)
            startActivity(intent)
        }
        binding.bimbinganBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, MkActivity::class.java)
            startActivity(intent)
        }

    }
}