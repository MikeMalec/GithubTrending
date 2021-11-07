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
) {
    NavHost(navController = navController, startDestination = Screen.WelcomeScreen.route) {
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
        composable(route = Screen.Repo.route) {
            RepoScreen({}, {
                navController.popBackStack()
            })
        }
    }
}