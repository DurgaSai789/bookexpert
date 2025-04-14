package com.example.bookexpert.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookexpert.navigations.LoginScreen
import com.example.bookexpert.navigations.MainScreen


@Composable
fun LoginScreenComposable(navHostController: NavHostController) {

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Button(
            onClick = {
                navHostController.navigate(MainScreen){
                    popUpTo<LoginScreen>{
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Dummy Image – replace with your actual image
//                Image(
//                    painter = painterResource(id = R.drawable.ic_gmail), // replace with your resource
//                    contentDescription = "Gmail Icon",
//                    modifier = Modifier
//                        .size(24.dp)
//                        .padding(end = 8.dp)
//                )
                Text(
                    text = "Login with Gmail",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}