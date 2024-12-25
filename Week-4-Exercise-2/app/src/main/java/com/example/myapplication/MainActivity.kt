package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    var studentCode by remember { mutableStateOf("") }
//                    Column(modifier = Modifier.padding(innerPadding)
//                        .fillMaxSize()
//                        .wrapContentWidth(Alignment.CenterHorizontally)
//                        .wrapContentHeight(Alignment.CenterVertically)) {
//                        StudentCodeGreetingText(studentCode)
//                        StudentCodeTextField(studentCode, onTextChanged = {
//                            if (it.length <= 7 && it.all { character -> character.isDigit() }) {
//                                studentCode = it
//                            }
//                        })
//                    }

                    var temperature = remember { mutableStateOf("") }
                    var currentSelectedUnitText = remember { mutableStateOf("") }
                    var temperatureResultText = remember { mutableStateOf("") }

                    Column(modifier = Modifier.padding(innerPadding)
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)) {

                        TemperatureTextField(temperature, onTemperatureChanged = {
                            temperature.value = it
                        })

                        TemperatureRadioButtonGroup(currentSelectedUnitText,
                            onTemperatureUnitSelected = { selection ->
                                currentSelectedUnitText.value = selection
                            })

                        TemperatureButton(onConversionClicked = {
                            if (currentSelectedUnitText.value == "C") {
                                val convertedValue = (temperature.value.toFloat() * 1.8) + 32
                                temperatureResultText.value = convertedValue.toString()
                            } else {
                                val convertedValue = (temperature.value.toFloat() - 32) / 18
                                temperatureResultText.value = convertedValue.toString()
                            }
                        })

                        TemperatureConversionResultText(temperatureResultText)
                    }
                }
            }
        }
    }
}

@Composable
fun TemperatureTextField(temperature: State<String>, onTemperatureChanged: (String) -> Unit) {
    TextField(value = temperature.value, onValueChange = onTemperatureChanged)
}

@Composable
fun TemperatureRadioButtonGroup(currentSelectedUnitText: State<String>,
                                onTemperatureUnitSelected: (String) -> Unit) {
    Row {
        TemperatureRadioButton("C", currentSelectedUnitText, onTemperatureUnitSelected)
        TemperatureRadioButton("F", currentSelectedUnitText, onTemperatureUnitSelected)
    }
}

@Composable
fun TemperatureRadioButton(unitText: String,
                           currentSelectedUnitText: State<String>,
                           onTemperatureUnitSelected: (String) -> Unit) {
    Row {
        RadioButton(selected = currentSelectedUnitText.value == unitText, onClick = {
            onTemperatureUnitSelected(unitText)
        })
        Text(unitText)
    }
}

@Composable
fun TemperatureButton(onConversionClicked: () -> Unit) {
    Button(onClick = onConversionClicked) {
        Text("Convert")
    }
}

@Composable
fun TemperatureConversionResultText(result: State<String>) {
    Text("Result: ${ result.value }")
}

@Composable
fun StudentCodeGreetingText(studentCode: String) {
    Text("Hello $studentCode", modifier = Modifier.fillMaxWidth()
        .wrapContentWidth(Alignment.CenterHorizontally))
}

@Composable
fun StudentCodeTextField(currentText: String, onTextChanged: (String) -> Unit) {
    Column {
        TextField(value = currentText,
            onValueChange = onTextChanged,
            modifier = Modifier.padding(16.dp),
            label = {
                Text("Student Code")
            })
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}