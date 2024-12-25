package com.example.starterapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.starterapplication.ui.theme.StarterApplicationTheme

private var currentText = ""
class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity_State"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate Called")

        enableEdgeToEdge()
        setContent {
            StarterApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("currentText", currentText)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        currentText = savedInstanceState.getString("currentText") ?: "'"
        super.onRestoreInstanceState(savedInstanceState)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf(currentText) }
    Column (modifier = modifier.fillMaxSize()
        .wrapContentWidth(Alignment.CenterHorizontally)
        .wrapContentHeight(Alignment.CenterVertically)) {
        TextField(
            value = text,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() } && newValue.length <= 7) {
                    text = newValue
                    currentText = newValue
                }
            },
            placeholder = {Text("Student Code")},
            label = {Text("Student Code")}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StarterApplicationTheme {
        Greeting("Android")
    }
}