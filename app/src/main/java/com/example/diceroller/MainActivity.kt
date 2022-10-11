package com.example.diceroller

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    Choice()
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Choice() {
    var choices by remember { mutableStateOf(0) }

    var backgroundColor by remember { mutableStateOf(Pink) }
    backgroundColor = if (choices == 0) Pink else Red
    var containerColor by remember { mutableStateOf(Pink) }
    containerColor = if (choices == 0) White else Black

    Scaffold(
        bottomBar = {
        choices = NavigationBarWithOnlySelectedLabelsSample(containerColor)
    }) {
        DiceWithButtonAndImage(
            Modifier
                .background(backgroundColor)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            choices = choices
        )
    }
}

@Composable
fun DiceWithButtonAndImage(
    modifier: Modifier = Modifier, choices: Int
) {
    var result by remember { mutableStateOf(1) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var imageResource = R.drawable.dice_7
        if (choices == 0) {
            imageResource = when (result) {
                1 -> R.drawable.dice_7
                2 -> R.drawable.dice_8
                3 -> R.drawable.dice_9
                4 -> R.drawable.dice_10
                5 -> R.drawable.dice_11
                else -> R.drawable.dice_12
            }
        } else {
            imageResource = when (result) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
        }
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Image(painter = painterResource(id = imageResource), contentDescription = result.toString())
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..6).random() }) {
            Text(stringResource(R.string.roll))
        }
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
fun NavigationBarWithOnlySelectedLabelsSample(containerColor: Color): Int {
    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar(containerColor = containerColor) {
        NavigationBarItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.dice_7),
                    contentDescription = "Pink"
                )
            },
            //label = { Text("Pink") },
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 },

            )

        NavigationBarItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.dice_1),
                    contentDescription = "Red"
                )
            },
            //label = { Text("Red") },
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 },

            )
    }
    return selectedItem
}