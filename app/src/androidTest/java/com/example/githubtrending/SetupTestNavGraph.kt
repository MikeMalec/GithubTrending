package com.example.githubtrending

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.githubtrending.screens.BottomBarScreen
import com.example.githubtrending.screens.MainActivity
import com.example.githubtrending.screens.Screen
import com.example.githubtrending.screens.repo.RepoScreen
import com.example.githubtrending.screens.savedrepos.SavedRepos
import com.example.githubtrending.screens.searchrepos.SearchReposScreen
import com.example.githubtrending.screens.welcome.WelcomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupTestNavGraph(
    navController: NavHostController,
    showBottomBar: () -> Unit,
    hideBottomBar: () -> Unit,
    openUrl: (url: String) -> Unit
) {
    NavHost(navController = navController, startDestination = Screen.WelcomeScreen.route) {
        composable(route = Screen.WelcomeScreen.route) {
            hideBottomBar()
            WelcomeScreen(hideBottomBar) {
                navController.navigate(BottomBarScreen.SearchRepos.route)
            }
        }
        composable(route = BottomBarScreen.SearchRepos.route) {
            showBottomBar()
            SearchReposScreen {
                MainActivity.repo = it
                navController.navigate(Screen.Repo.route)
            }
        }
        composable(route = BottomBarScreen.SavedRepos.route) {
            showBottomBar()
            SavedRepos({
                MainActivity.repo = it
                navController.navigate(Screen.Repo.route)
            })
        }
        composable(route = Screen.Repo.route) {
            hideBottomBar()
            RepoScreen(openUrl, {
                showBottomBar()
                navController.popBackStack()
            })
        }
    }
}