package com.example.githubtrending.screens

import com.example.githubtrending.R

sealed class Screen(val route: String) {
    object WelcomeScreen : Screen(route = "welcome_screen")
    object Repo : Screen(route = "repo")
    object WebScreen : Screen(route = "web/{url}/{title}")
}

sealed class BottomBarScreen(val route: String, val title: String, val icon: Int) {
    object SearchRepos : BottomBarScreen(
        route = "search_repos",
        title = "Trending",
        icon = R.drawable.ic_baseline_trending_up_24
    )

    object SavedRepos : BottomBarScreen(
        route = "saved_repos",
        title = "Saved",
        icon = R.drawable.ic_baseline_save_24
    )
}