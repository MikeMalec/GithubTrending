package com.example.githubtrending.ui.welcome

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.githubtrending.screens.MainActivity
import com.example.githubtrending.screens.welcome.WelcomeScreen
import com.example.githubtrending.ui.theme.GithubTrendingTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@ExperimentalAnimationApi
@HiltAndroidTest
class WelcomeScreenTest {
    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun click_search_repos_confirm_navigated_forward() {
        var hideBottomBar = false
        var navigationCallback = false
        composeTestRule.setContent {
            GithubTrendingTheme {
                WelcomeScreen(
                    hideBottomBar = { hideBottomBar = true },
                    navigationCallback = { navigationCallback = true })
            }
        }
        composeTestRule.onNodeWithText("Search Repos").performClick()
        assert(hideBottomBar)
        assert(navigationCallback)
    }
}