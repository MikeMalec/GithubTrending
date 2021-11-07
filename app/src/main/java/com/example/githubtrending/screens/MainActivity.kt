package com.example.githubtrending.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import androidx.compose.animation.*
import com.example.business.domain.Repo
import com.example.githubtrending.screens.components.BottomBar
import com.example.githubtrending.ui.theme.GithubTrendingTheme
import com.example.githubtrending.utils.CurrentRoute

import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        var repo: Repo? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubTrendingTheme {
                val navController = rememberAnimatedNavController()
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(bottomBar = {
                        val currentRoute = CurrentRoute(navController = navController)
                        if (currentRoute == BottomBarScreen.SavedRepos.route || currentRoute ==
                            BottomBarScreen.SearchRepos.route
                        ) {
                            BottomBar(navController = navController)
                        }
                    }) {
                        SetupNavGraph(navController = navController)
                    }
                }
            }
        }
    }
}
