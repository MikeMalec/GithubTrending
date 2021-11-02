package com.example.githubtrending.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*

import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import androidx.compose.animation.*

import com.example.business.domain.Repo
import com.example.githubtrending.screens.components.BottomBar
import com.example.githubtrending.ui.theme.GithubTrendingTheme

import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        var repo: Repo? = null
    }

    /**
     * ui tests
     * commit
     * ci gh actions-> tests and build
     * dark white mode
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubTrendingTheme {
                val navController = rememberAnimatedNavController()
                Surface(color = MaterialTheme.colors.background) {
                    var showBottomBar by remember {
                        mutableStateOf(false)
                    }
                    Scaffold(bottomBar = {
                        if (showBottomBar) {
                            BottomBar(navController = navController)
                        }
                    }) {
                        SetupNavGraph(
                            navController = navController,
                            showBottomBar = { showBottomBar = true },
                            hideBottomBar = { showBottomBar = false },
                            openUrl = ::openUrl
                        )
                    }
                }
            }
        }
    }

    private fun openUrl(url: String) {
        Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(url)
            startActivity(this)
        }
    }
}
