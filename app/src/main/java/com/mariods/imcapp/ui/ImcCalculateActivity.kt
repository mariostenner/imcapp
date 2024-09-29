package com.mariods.imcapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.mariods.imcapp.R
import java.text.DecimalFormat

class ImcCalculateActivity : AppCompatActivity() {

    private var currentHeight = 1.20f
    private var currentWeight = 70
    private var currentAge = 30
    private var gender = true
    private val df = DecimalFormat("#.##")

    private lateinit var cvMale: CardView
    private lateinit var cvFemale: CardView
    private lateinit var tvHeight: AppCompatTextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvWeight: AppCompatTextView
    private lateinit var btnRemoveWeight: FloatingActionButton
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var tvAge: AppCompatTextView
    private lateinit var btnRemoveAge: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var btnCalculateImc: AppCompatButton
    private lateinit var cvHeight: CardView
    private lateinit var cvWeight: CardView
    private lateinit var cvAge: CardView

    companion object {
        const val IMC_RESULT = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculate)

        initComponents()
        initListeners()
        initUI()

    }

    private fun initComponents() {
        cvMale = findViewById(R.id.cvMale)
        cvFemale = findViewById(R.id.cvFemale)
        tvHeight = findViewById(R.id.tvheight)
        rsHeight = findViewById(R.id.rsHeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnRemoveWeight = findViewById(R.id.btnRemoveHeight)
        btnAddWeight = findViewById(R.id.btnAddHeight)
        tvAge = findViewById(R.id.tvAge)
        btnRemoveAge = findViewById(R.id.btnRemoveAge)
        btnAddAge = findViewById(R.id.btnAddAge)
        btnCalculateImc = findViewById(R.id.btnCalculate)
        cvHeight = findViewById(R.id.cvHeight)
        cvWeight = findViewById(R.id.cvWeight)
        cvAge = findViewById(R.id.cvAge)

    }

    private fun initListeners() {
        cvMale.setOnClickListener {
            gender = true
            setGenderColors()
        }
        cvFemale.setOnClickListener {
            gender = false
            setGenderColors()
        }

        rsHeight.addOnChangeListener { _, value, _ ->
            currentHeight = value
            tvHeight.text = "${df.format(currentHeight).toString()} m"
        }

        btnRemoveWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        btnAddWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }

        btnRemoveAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        btnAddAge.setOnClickListener {
            currentAge += 1
            setAge()
        }

        btnCalculateImc.setOnClickListener { navigateToresult(calculateIMC()) }
    }

    private fun initUI() {
        setGenderColors()
        setWeight()
        setAge()
    }

    private fun setGenderColors() {
        val maleColor = getColor(R.color.green4)
        val femaleColor = getColor(R.color.purple_200)
        if (gender) {
            cvMale.setCardBackgroundColor(maleColor)
            cvHeight.setCardBackgroundColor(maleColor)
            cvWeight.setCardBackgroundColor(maleColor)
            cvAge.setCardBackgroundColor(maleColor)
        } else {
            cvFemale.setCardBackgroundColor(femaleColor)
            cvHeight.setCardBackgroundColor(femaleColor)
            cvWeight.setCardBackgroundColor(femaleColor)
            cvAge.setCardBackgroundColor(femaleColor)
        }
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun calculateIMC() =
        df.format(currentWeight / (currentHeight * currentHeight)).toDouble()

    private fun navigateToresult(resultIMC: Double) {
        val intentResult = Intent(this, ImcResultActivity::class.java)
        intentResult.putExtra(IMC_RESULT, resultIMC)
        startActivity(intentResult)
    }
}