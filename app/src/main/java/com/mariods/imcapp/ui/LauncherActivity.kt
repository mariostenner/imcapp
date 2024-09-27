package com.mariods.imcapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mariods.imcapp.R
import com.mariods.imcapp.ui.theme.ImcappTheme
import com.mariods.imcapp.ui.theme.*

class LauncherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImcappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LauncherApp(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LauncherApp(name: String, modifier: Modifier = Modifier) {

    LauncherAppPreview()

}


@Preview(showBackground = true)
@Composable
fun LauncherAppPreview() {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(green2)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                val intent = Intent(context, ImcCalculateActivity::class.java)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier.size(300.dp, 100.dp)
        ) {
            Text(text = "Open IMC APP", fontSize = 30.sp, color = green1)
        }
    }
}

