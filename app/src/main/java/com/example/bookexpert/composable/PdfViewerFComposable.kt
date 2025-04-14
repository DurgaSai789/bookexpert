package com.example.bookexpert.composable

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.delay

@Composable
fun PdfViewerFComposable() {
    val pdfUrl = "https://fssservices.bookxpert.co/GeneratedPDF/Companies/nadc/2024-2025/BalanceSheet.pdf"
    var showPdf  = remember { mutableStateOf(false) }

    // Delay PDF viewer init until Composable is drawn
    LaunchedEffect(Unit) {
        // Delay by a few milliseconds to allow UI to render the top part
        delay(100)
        showPdf.value = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "PDF Viewer",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                )
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.Gray)
        )

        // Only load PDF when UI has had time to show the header
        if (showPdf.value) {
            PdfViewerScreenWithLoader(pdfUrl, modifier = Modifier)
        }
    }
}

@Composable
fun PdfViewerScreenWithLoader(pdfUrl: String, modifier: Modifier) {
    var isLoading = remember { mutableStateOf(true) }

    Box(modifier = modifier) {
        AndroidView(
            modifier = modifier.fillMaxWidth().fillMaxHeight(),
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.allowFileAccess = true
                    settings.domStorageEnabled = true

                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                            isLoading.value = true
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            isLoading.value = false
                        }
                    }

                    loadUrl("https://docs.google.com/gview?embedded=true&url=$pdfUrl")
                }
            }
        )

        if (isLoading.value) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
