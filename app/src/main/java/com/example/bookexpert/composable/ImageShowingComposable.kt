package com.example.bookexpert.composable

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter


@Composable
fun ImageShowingComposable() {
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val cameraImageUri = remember { mutableStateOf<Uri?>(null) }

    // Gallery Picker
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri.value = it
        }
    }

    // Camera Capture
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            imageUri.value = cameraImageUri.value
        }
    }

    // Permission Launchers
    val galleryPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            galleryLauncher.launch("image/*")
        } else {
            Toast.makeText(context, "Permission denied for gallery", Toast.LENGTH_SHORT).show()
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val uri = createImageUri(context)
            cameraImageUri.value = uri
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission denied for camera", Toast.LENGTH_SHORT).show()
        }
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
                text = "Image",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.Gray)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = {
                        // Check camera permission
                        if (ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.CAMERA
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            val uri = createImageUri(context)
                            cameraImageUri.value = uri
                            cameraLauncher.launch(uri)
                        } else {
                            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Capture Image")
                }

                Button(
                    onClick = {
                        val galleryPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                            Manifest.permission.READ_MEDIA_IMAGES
                        else
                            Manifest.permission.READ_EXTERNAL_STORAGE

                        if (ContextCompat.checkSelfPermission(
                                context,
                                galleryPermission
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            galleryLauncher.launch("image/*")
                        } else {
                            galleryPermissionLauncher.launch(galleryPermission)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Gallery Pick")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Display Image
            imageUri.value?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(10.dp))
                        .border(1.dp, Color.Gray)
                )
            }
        }
    }
}

// Function to create image URI for camera
fun createImageUri(context: Context): Uri {
    val resolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(
            MediaStore.Images.Media.DISPLAY_NAME,
            "Captured_Image_${System.currentTimeMillis()}.jpg"
        )
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
    }
    return resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)!!
}
