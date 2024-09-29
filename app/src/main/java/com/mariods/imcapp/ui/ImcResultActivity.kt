package com.mariods.imcapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mariods.imcapp.R
import com.mariods.imcapp.ui.ImcCalculateActivity.Companion.IMC_RESULT
import com.mariods.imcapp.ui.theme.green3
import com.mariods.imcapp.ui.theme.green4
import com.mariods.imcapp.ui.ui.theme.ImcappTheme

class ImcResultActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImcappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ImcAppResult(
                        extraResult = intent?.getDoubleExtra(IMC_RESULT, 0.0) ?: 0.0,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


}

@Composable
fun ImcAppResult(extraResult: Double, modifier: Modifier = Modifier) {

    val context = LocalContext.current

    var levelImc = ""
    var descImc = ""

    when (extraResult) {
        in 0.0..18.0 -> {
            levelImc = stringResource(id = R.string.imc_low_level)
            descImc = stringResource(id = R.string.imc_low_level_desc)
        }

        in 18.1..25.0 -> {
            levelImc = stringResource(id = R.string.imc_normal_level)
            descImc = stringResource(id = R.string.imc_normal_level_desc)
        }

        in 25.1..30.0 -> {
            levelImc = stringResource(id = R.string.imc_high_level)
            descImc = stringResource(id = R.string.imc_high_level_desc)
        }

        else -> {
            levelImc = stringResource(id = R.string.imc_error_level)
            descImc = stringResource(id = R.string.imc_error_level)
        }

    }

    val imcResult by rememberSaveable {
        mutableDoubleStateOf(extraResult)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = green3)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.imc_tittle_result),
            color = Color.White,
            fontSize = 32.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(32.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(green4)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = levelImc.uppercase(),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = imcResult.toString(),
                    color = Color.White,
                    fontSize = 60.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = descImc,
                    color = Color.LightGray,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )

            }

        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 0.dp, 32.dp)
        ) {
            Button(
                onClick = {
                    Log.i("RECALCULAR", "Se presiono el boton recalcular")

                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .size(100.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonColors(
                    contentColor = green4,
                    containerColor = Color.White,
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                )
            ) {
                Text(
                    text = stringResource(id = R.string.imc_button_recalculate).uppercase(),
                    fontSize = 24.sp
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun ImcAppResultPreview() {

    ImcAppResult(0.0)

}
