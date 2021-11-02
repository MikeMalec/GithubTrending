package com.example.githubtrending.screens.welcome

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framework.utils.Constants
import com.example.githubtrending.R

@Composable
fun WelcomeScreen(hideBottomBar: () -> Unit, navigationCallback: () -> Unit) {
    LaunchedEffect(key1 = true) {
        hideBottomBar()
    }
    Box(modifier = Modifier.fillMaxSize()) {
        val infiniteTransition = rememberInfiniteTransition()
        val size by infiniteTransition.animateFloat(
            initialValue = 50f,
            targetValue = 65f,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = 750, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        Image(
            painter = painterResource(id = R.drawable.bggh),
            contentDescription = "bg",
            contentScale = ContentScale.Crop,
            alpha = 0.5f,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Github Trendings", fontWeight = FontWeight.Bold, fontSize = 44.sp)
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(1.dp, 150.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_gh),
                contentDescription = "gh_logo", modifier = Modifier.size(size.dp, size.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 0.dp, 32.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navigationCallback() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp)
                    .testTag(Constants.TestTags.WELCOME_BTN)
                ,
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(text = "Search Repos", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}