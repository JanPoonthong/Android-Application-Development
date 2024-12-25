package com.example.myapplication

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import com.example.myapplication.ui.theme.MyApplicationTheme

private var currentText = ""
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var studentCode by remember { mutableStateOf(currentText) }
                    Column (modifier = Modifier.padding(innerPadding).fillMaxSize()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .wrapContentHeight(Alignment.CenterVertically)) {
                        StudentCodeGreetingText(studentCode)
                        StudentCodeTextField(
                            studentCode, onTextChange = {
                                if (it.length <= 7 && it.all { character -> character.isDigit() }) {
                                    studentCode = it
                                }
                            },
                        )
                    }
                }
            }
        }
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
fun StudentCodeTextField(currentText: String, onTextChange: (String) -> Unit) {
    Column {
        TextField(
            value = currentText,
            onValueChange = onTextChange,
            placeholder = { Text("Student Code") },
            label = { Text("Student Code") }
        )
    }
}

@Composable
fun StudentCodeGreetingText(studentCode: String) {
    Text("Hello $studentCode", modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        StudentCodeGreetingText(currentText)
    }
}