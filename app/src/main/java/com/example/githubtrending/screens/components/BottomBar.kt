package com.example.githubtrending.screens.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.githubtrending.screens.BottomBarScreen

@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(BottomBarScreen.SearchRepos, BottomBarScreen.SavedRepos)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        modifier = Modifier
            .height(60.dp)
            .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))

    ) {
        screens.forEach { screen ->
            val isSelected = currentDestination!!.route == screen.route
            BottomNavigationItem(
                label = { Text(text = screen.title) },
                icon = {
                    Icon(painter = painterResource(id = screen.icon), contentDescription = "")
                },
                selected = isSelected,
                unselectedContentColor = LocalContentColor.current.copy(
                    alpha = ContentAlpha
                        .disabled
                ),
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                })
        }
    }
}