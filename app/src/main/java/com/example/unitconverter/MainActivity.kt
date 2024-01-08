package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember{ mutableStateOf("")  }
    var outputValue by remember{ mutableStateOf("") }
    var inputUnit by remember{ mutableStateOf("Meters") }
    var outputUnit by remember{ mutableStateOf("Meters") }
    var iExpanded by remember{ mutableStateOf(false) }
    var oExpanded by remember{ mutableStateOf(false) }
    var conversionFactor by remember{ mutableStateOf(1.00) }
    var oConversionFactor by remember{ mutableStateOf(1.00) }

    val customTextStyle= TextStyle(
        fontFamily= FontFamily.Default,
        fontSize=16.sp,
        color= Color.Red
    )

    fun convertUnits() {
        val inputValueDouble= inputValue.toDoubleOrNull() ?:0.0
        val result=(inputValueDouble * conversionFactor* 100.0 / oConversionFactor).roundToInt() /100.0
        outputValue=result.toString()
    }
    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement=Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //here the ui elements will be stacked below each other
        Text("Unit Converter",style =MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = {
                inputValue=it
                convertUnits()
                //here goes what should happen, when the value of our OutlinedTextField changes
            },
            label = {Text("enter value")}
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            // here elements will be stacked next to each other
            // Input Box
            Box {
                //Input Button
                Button(onClick = { iExpanded= true}) {
                    Text(inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down "
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { /*TODO*/ }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = { iExpanded=false
                            inputUnit= "Centimeters"
                            conversionFactor =0.01
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = { iExpanded=false
                            inputUnit="Meters"
                            conversionFactor=1.0
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Milimeters") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Milimeters"
                            conversionFactor=0.001
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Feet"
                            conversionFactor=0.3048
                            convertUnits()
                        })
                }

            }

            Spacer(modifier = Modifier.width(16.dp))
            //output box
            Box {
                //output button
                Button(onClick = { oExpanded= true }) {
                    Text(outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down "
                    )

                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = { oExpanded=false
                            outputUnit= "Centimeters"
                            oConversionFactor =0.01
                            convertUnits() })
                    DropdownMenuItem(
                        text = { Text("Meters") }, onClick = {oExpanded=false
                            outputUnit= "Meters"
                            oConversionFactor =1.0
                            convertUnits() })
                    DropdownMenuItem(
                        text = { Text("Milimeters") }, onClick = { oExpanded=false
                            outputUnit= "Milimeters"
                            oConversionFactor =0.001
                            convertUnits() })
                    DropdownMenuItem(
                        text = { Text("Feet") }, onClick = {  oExpanded=false
                            outputUnit= "Feet"
                            oConversionFactor =0.3048
                            convertUnits()
                        })
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        //Result text
        Text("Result: $outputValue $outputUnit",
        style =MaterialTheme.typography.headlineMedium
        )
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}








