package com.example.githubtrending.ui.endToEnd

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.business.data.cache.repo.RepoCacheDataSource
import com.example.business.data.network.programminglanguage.ProgrammingLanguageNetworkDataSource
import com.example.business.data.network.repo.RepoNetworkDataSource
import com.example.business.data.preferences.AppPreferences
import com.example.business.domain.Period.*
import com.example.framework.utils.Constants
import com.example.githubtrending.SetupTestNavGraph
import com.example.githubtrending.data.TestRepoFactory
import com.example.githubtrending.data.cache.repo.FakeRepoCacheDataSource
import com.example.githubtrending.data.network.programminglanguages.FakeProgrammingLanguageNetworkDataSource
import com.example.githubtrending.data.network.repo.FakeRepoNetworkDataSource
import com.example.githubtrending.data.preferences.FakeAppPreferences
import com.example.githubtrending.screens.BottomBarScreen
import com.example.githubtrending.screens.MainActivity
import com.example.githubtrending.screens.Screen
import com.example.githubtrending.screens.components.BottomBar
import com.example.githubtrending.ui.theme.GithubTrendingTheme
import com.example.githubtrending.utils.CurrentRoute
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@ExperimentalAnimationApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class EndToEndTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var repoCacheDataSource: RepoCacheDataSource
    private val fakeRepoCacheDataSource: FakeRepoCacheDataSource
        get() = repoCacheDataSource as FakeRepoCacheDataSource

    @Inject
    lateinit var repoNetworkDataSource: RepoNetworkDataSource
    private val fakeRepoNetworkDataSource: FakeRepoNetworkDataSource
        get() = repoNetworkDataSource as FakeRepoNetworkDataSource

    @Inject
    lateinit var fakeLanguagesDataSource: ProgrammingLanguageNetworkDataSource

    @Inject
    lateinit var fakeAppPreferences: AppPreferences

    @Before
    fun init() {
        hiltRule.inject()
        (fakeLanguagesDataSource as FakeProgrammingLanguageNetworkDataSource).programmingLanguages.addAll(
            listOf("c", "c++", "c#", "kotlin", "java")
        )
        (fakeAppPreferences as FakeAppPreferences).let {
            it.language = "kotlin"
            it.period = "daily"
        }
        composeTestRule.setContent {
            GithubTrendingTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(bottomBar = {
                        val currentRoute = CurrentRoute(navController = navController)
                        if (currentRoute != Screen.Repo.route && currentRoute != Screen.WelcomeScreen.route) {
                            BottomBar(navController = navController)
                        }
                    }) {
                        SetupTestNavGraph(
                            navController = navController,
                            openUrl = {}
                        )
                    }
                }
            }
        }
    }

    @Test
    fun open_app_navigate_to_search_screen_show_repo_back_to_search_screen() {
        fakeRepoNetworkDataSource.daily.addAll(
            TestRepoFactory.createRepos(
                1..9,
                "kotlin"
            )
        )
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("TAG")
        composeTestRule.onNodeWithTag(Constants.TestTags.WELCOME_BTN).performClick()
        composeTestRule.onNodeWithText("Trending Repos").assertExists()
        composeTestRule.onNodeWithTag("LIST_INDEX_1").performClick()
        composeTestRule.onNodeWithText("daily Project in kotlin").assertExists()
        composeTestRule.onNodeWithText("Delete").assertExists()
        composeTestRule.onNodeWithText("Show repo").assertExists()
        composeTestRule.onNodeWithTag(Constants.TestTags.BACK_BTN).performClick()
        composeTestRule.onNodeWithText("Trending Repos").assertExists()
    }

    @Test
    fun open_app_navigate_to_search_screen_show_repo_save_go_to_saved_confirm_repo_saved() {
        fakeRepoNetworkDataSource.daily.addAll(
            TestRepoFactory.createRepos(
                1..9,
                "kotlin"
            )
        )
        fakeRepoNetworkDataSource.daily.first().cacheId = null
        composeTestRule.onNodeWithTag(Constants.TestTags.WELCOME_BTN).performClick()
        composeTestRule.onNodeWithTag("LIST_INDEX_null").performClick()
        composeTestRule.onNodeWithTag(Constants.TestTags.SAVE_BTN).performClick()
        composeTestRule.onNodeWithTag(Constants.TestTags.BACK_BTN).performClick()
        composeTestRule.onNodeWithTag(BottomBarScreen.SavedRepos.title).performClick()
        composeTestRule.onNodeWithText("Saved Repos").assertExists()
        composeTestRule.onNodeWithText("daily Project in kotlin").assertExists()
    }

    @Test
    fun open_app_navigate_to_saved_screen_show_repo_delete_back_confirm_repo_saved() {
        fakeRepoCacheDataSource.repos.add(TestRepoFactory.createRepos(0..1).first())
        composeTestRule.onNodeWithTag(Constants.TestTags.WELCOME_BTN).performClick()
        composeTestRule.onNodeWithText(BottomBarScreen.SavedRepos.title).performClick()
        composeTestRule.onNodeWithText("daily Project in c").assertExists()
        composeTestRule.onNodeWithTag("LIST_INDEX_0").performClick()
        composeTestRule.onNodeWithTag(Constants.TestTags.DELETE_BTN).performClick()
        composeTestRule.onNodeWithTag(Constants.TestTags.BACK_BTN).performClick()
        composeTestRule.onNodeWithText("daily Project in c").assertDoesNotExist()
    }

    @Test
    fun open_app_navigate_to_search_repos_change_filter_to_weekly_confirm_right_repos_displayed() {
        fakeRepoNetworkDataSource.daily.addAll(
            TestRepoFactory.createRepos(
                1..5,
                "kotlin",
            )
        )
        fakeRepoNetworkDataSource.weekly.addAll(
            TestRepoFactory.createRepos(
                1..7,
                "kotlin",
                Weekly
            )
        )
        composeTestRule.onNodeWithTag(Constants.TestTags.WELCOME_BTN).performClick()
        composeTestRule.onNodeWithTag(Constants.TestTags.SHOW_FILTERS).performClick()
        composeTestRule.onNodeWithText("Weekly").performClick()
        composeTestRule.onNodeWithTag(Constants.TestTags.ACCEPT_FILTER).performClick()
        composeTestRule.onAllNodesWithTag(Constants.TestTags.REPO_DESC)
            .assertAll(hasText("weekly Project in kotlin"))
    }

    @Test
    fun open_app_navigate_to_search_repos_change_filter_to_monthly_confirm_right_repos_displayed() {
        fakeRepoNetworkDataSource.daily.addAll(
            TestRepoFactory.createRepos(
                1..5,
                "kotlin",
            )
        )
        fakeRepoNetworkDataSource.weekly.addAll(
            TestRepoFactory.createRepos(
                1..7,
                "kotlin",
                Monthly
            )
        )
        composeTestRule.onNodeWithTag(Constants.TestTags.WELCOME_BTN).performClick()
        composeTestRule.onNodeWithTag(Constants.TestTags.SHOW_FILTERS).performClick()
        composeTestRule.onNodeWithText("Monthly").performClick()
        composeTestRule.onNodeWithTag(Constants.TestTags.ACCEPT_FILTER).performClick()
        composeTestRule.onAllNodesWithTag(Constants.TestTags.REPO_DESC)
            .assertAll(hasTestTag("monthly Project in kotlin"))
    }

    @Test
    fun open_app_navigate_to_search_repos_change_filter_to_monthly_c_confirm_right_repos_displayed() {
        fakeRepoNetworkDataSource.daily.addAll(
            TestRepoFactory.createRepos(
                1..5,
                "kotlin",
            )
        )
        fakeRepoNetworkDataSource.monthly.addAll(
            TestRepoFactory.createRepos(
                1..7,
                "c++",
                Monthly
            )
        )
        composeTestRule.onNodeWithTag(Constants.TestTags.WELCOME_BTN).performClick()
        composeTestRule.onNodeWithTag(Constants.TestTags.SHOW_FILTERS).performClick()
        composeTestRule.onNodeWithText("Monthly").performClick()
        composeTestRule.onNodeWithTag(Constants.TestTags.PICK_LANGUAGE).performClick()
        composeTestRule.onNodeWithText("c++").performClick()
        composeTestRule.onNodeWithTag(Constants.TestTags.ACCEPT_FILTER).performClick()
        composeTestRule.onAllNodesWithTag(Constants.TestTags.REPO_DESC)
            .assertAll(hasTestTag("monthly Project in c++"))
    }
}