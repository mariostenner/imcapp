package com.mariods.imcapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mariods.imcapp.R

class ImcCalculateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculate)

        initComponents()
        initListeners()
        initUI()

    }

    private fun initComponents() {
        TODO("Not yet implemented")
    }

    private fun initListeners() {
        TODO("Not yet implemented")
    }

    private fun initUI() {
        TODO("Not yet implemented")
    }
}