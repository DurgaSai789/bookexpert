package com.example.bookexpert.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bookexpert.navigations.APIListScreen
import com.example.bookexpert.navigations.ImageShowingScreen
import com.example.bookexpert.navigations.LoginScreen
import com.example.bookexpert.navigations.PDFScreen


@Composable
fun MainScreenComposable(navHostController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        CurvedCardItem(text = "Pdf Viewer") {
            navHostController.navigate(PDFScreen)
        }
        CurvedCardItem(text = "Image Capture & Gallery Section"){
            navHostController.navigate(ImageShowingScreen)
        }
        CurvedCardItem(text = "API List"){
            navHostController.navigate(APIListScreen)
        }
    }
}

@Composable
fun CurvedCardItem(text: String,click : () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth().clickable {
            click.invoke()
        }
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp        ,
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}
