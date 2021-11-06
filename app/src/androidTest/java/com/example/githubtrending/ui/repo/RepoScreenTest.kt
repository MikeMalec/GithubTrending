package com.example.githubtrending.ui.repo

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.business.domain.Repo
import com.example.business.usecases.DeleteRepo
import com.example.business.usecases.SaveRepo
import com.example.githubtrending.screens.MainActivity
import com.example.githubtrending.screens.repo.RepoScreen
import com.example.githubtrending.screens.repo.RepoViewModel
import com.example.githubtrending.ui.theme.GithubTrendingTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalAnimationApi
@HiltAndroidTest
class RepoScreenTest {

    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var saveRepo: SaveRepo

    @Inject
    lateinit var deleteRepo: DeleteRepo

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun is_not_saved_repo_data_shown() {
        val repo = Repo(
            cacheId = null,
            author = "TestAuthor",
            name = "Test",
            langColor = "#A97BFF",
            description = "desc",
            url = "",
            stars = 1,
            forks = 2,
            language = "c",
            avatar = ""
        )
        MainActivity.repo = repo
        val vm = RepoViewModel(saveRepo, deleteRepo, Dispatchers.Main)
        composeTestRule.setContent {
            GithubTrendingTheme {
                RepoScreen(openUrl = {}, popBackStack = { }, viewModel = vm)
            }
        }
        composeTestRule.onNodeWithText("TestAuthor").assertExists()
        composeTestRule.onNodeWithText("Test").assertExists()
        composeTestRule.onNodeWithText("desc").assertExists()
        composeTestRule.onNodeWithText("1").assertExists()
        composeTestRule.onNodeWithText("2").assertExists()
        composeTestRule.onNodeWithText("Save").assertExists()
    }

    @Test
    fun is_saved_repo_data_shown() {
        val repo = Repo(
            cacheId = 1,
            author = "TestAuthor",
            name = "Test",
            langColor = "#A97BFF",
            description = "desc",
            url = "",
            stars = 1,
            forks = 2,
            language = "c",
            avatar = ""
        )
        MainActivity.repo = repo
        val vm = RepoViewModel(saveRepo, deleteRepo, Dispatchers.Main)
        composeTestRule.setContent {
            GithubTrendingTheme {
                RepoScreen(openUrl = {}, popBackStack = { }, viewModel = vm)
            }
        }
        composeTestRule.onNodeWithText("TestAuthor").assertExists()
        composeTestRule.onNodeWithText("Test").assertExists()
        composeTestRule.onNodeWithText("desc").assertExists()
        composeTestRule.onNodeWithText("1").assertExists()
        composeTestRule.onNodeWithText("2").assertExists()
        composeTestRule.onNodeWithText("Delete").assertExists()
    }
}