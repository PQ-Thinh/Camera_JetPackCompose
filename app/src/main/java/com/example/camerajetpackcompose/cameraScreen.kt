package com.example.camerajetpackcompose

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun CameraScreen(){

    val context = LocalContext.current

    val lifecycleOwner = LocalLifecycleOwner.current

    val previewView: PreviewView = remember {
        PreviewView(context)
    }

    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    val preview = Preview.Builder().build()

    val imageCapture = remember {
        ImageCapture.Builder().build()
    }
}

private suspend fun Context.getCameraProvider():ProcessCameraProvider = suspendCoroutine { continuation ->

    val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

    cameraProviderFuture.addListener({
        continuation.resume(cameraProviderFuture.get())
    }, ContextCompat.getMainExecutor(this))
}