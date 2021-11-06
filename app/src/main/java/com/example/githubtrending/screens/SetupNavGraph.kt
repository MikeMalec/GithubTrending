package com.example.githubtrending.screens

import androidx.compose.runtime.Composable


import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.navigation.NavHostController

import com.example.githubtrending.screens.repo.RepoScreen
import com.example.githubtrending.screens.savedrepos.SavedRepos
import com.example.githubtrending.screens.searchrepos.SearchReposScreen
import com.example.githubtrending.screens.welcome.WelcomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    openUrl: (url: String) -> Unit
) {
    AnimatedNavHost(navController = navController, startDestination = Screen.WelcomeScreen.route) {
        composable(route = Screen.WelcomeScreen.route) {
            WelcomeScreen {
                navController.navigate(BottomBarScreen.SearchRepos.route)
            }
        }
        composable(route = BottomBarScreen.SearchRepos.route) {
            SearchReposScreen {
                MainActivity.repo = it
                navController.navigate(Screen.Repo.route)
            }
        }
        composable(route = BottomBarScreen.SavedRepos.route) {
            SavedRepos({
                MainActivity.repo = it
                navController.navigate(Screen.Repo.route)
            })
        }
        composable(route = Screen.Repo.route, enterTransition = { _, _ ->
            slideInVertically(
                initialOffsetY = { 600 }, animationSpec = tween
                    (durationMillis = 600, easing = FastOutSlowInEasing)
            ) + fadeIn(animationSpec = tween(600))
        },
            popExitTransition = { _, _ ->
                slideOutVertically(
                    targetOffsetY = { 600 }, animationSpec = tween
                        (durationMillis = 600, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(600))
            }) {
            RepoScreen(openUrl, {
                navController.popBackStack()
            })
        }
    }
}