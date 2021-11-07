package com.example.githubtrending.screens

import androidx.compose.runtime.Composable


import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.example.framework.utils.Constants

import com.example.githubtrending.screens.repo.RepoScreen
import com.example.githubtrending.screens.savedrepos.SavedRepos
import com.example.githubtrending.screens.searchrepos.SearchReposScreen
import com.example.githubtrending.screens.webscreen.WebScreen
import com.example.githubtrending.screens.welcome.WelcomeScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
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
            RepoScreen({
                val url = URLEncoder.encode(it.url, StandardCharsets.UTF_8.toString())
                val title = it.name
                navController.navigate("web/$url/$title")
            }, {
                navController.popBackStack()
            })
        }
        composable(route = Screen.WebScreen.route, arguments = listOf(
            navArgument("url") {
                type = NavType.StringType
            },
            navArgument("title") {
                type = NavType.StringType
            }
        )) {
            val url = it.arguments!!.getString("url")!!
            val title = it.arguments!!.getString("title")!!
            WebScreen(title = title, url = url) {
                navController.popBackStack()
            }
        }
    }
}