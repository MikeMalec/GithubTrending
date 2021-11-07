package com.example.githubtrending.screens.webscreen

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.githubtrending.R

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebScreen(title: String, url: String, popBackStack: () -> Unit) {
    var showLoading by remember {
        mutableStateOf(true)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(MaterialTheme.colors.primary),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .padding(16.dp, 8.dp, 8.dp, 8.dp)
                        .clickable {
                            popBackStack()
                        },
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                    contentDescription = "back_btn"
                )
                Text(modifier = Modifier.padding(8.dp), text = title, color = Color.White)
            }
            AndroidView(factory = {
                WebView(it).apply {
                    canGoBack()
                    canGoForward()
                    settings.javaScriptEnabled = true
                    webChromeClient = object : WebChromeClient() {
                        override fun onProgressChanged(view: WebView?, newProgress: Int) {
                            super.onProgressChanged(view, newProgress)
                            if (newProgress > 60) showLoading = false
                        }
                    }
                }
            }, update = { it.loadUrl(url) })
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (showLoading) {
                CircularProgressIndicator()
            }
        }
    }
}