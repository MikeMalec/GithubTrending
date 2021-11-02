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
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.business.data.cache.repo.RepoCacheDataSource
import com.example.business.data.network.programminglanguage.ProgrammingLanguageNetworkDataSource
import com.example.business.data.network.repo.RepoNetworkDataSource
import com.example.business.data.preferences.AppPreferences
import com.example.framework.utils.Constants
import com.example.githubtrending.SetupTestNavGraph
import com.example.githubtrending.data.TestRepoFactory
import com.example.githubtrending.data.cache.repo.FakeRepoCacheDataSource
import com.example.githubtrending.data.network.programminglanguages.FakeProgrammingLanguageNetworkDataSource
import com.example.githubtrending.data.network.repo.FakeRepoNetworkDataSource
import com.example.githubtrending.data.preferences.FakeAppPreferences
import com.example.githubtrending.screens.MainActivity
import com.example.githubtrending.screens.components.BottomBar
import com.example.githubtrending.ui.theme.GithubTrendingTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
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
    lateinit var fakeRepoCacheDataSource: RepoCacheDataSource

    @Inject
    lateinit var fakeRepoNetworkDataSource: RepoNetworkDataSource

    @Inject
    lateinit var fakeLanguagesDataSource: ProgrammingLanguageNetworkDataSource

    @Inject
    lateinit var fakeAppPreferences: AppPreferences

    @Before
    fun init() {
        hiltRule.inject()
        (fakeRepoCacheDataSource as FakeRepoCacheDataSource).repos.addAll(TestRepoFactory.createRepos(1..5, "c"))
        (fakeRepoNetworkDataSource as FakeRepoNetworkDataSource).daily.addAll(TestRepoFactory.createRepos(1..10, "kotlin"))
        (fakeLanguagesDataSource as FakeProgrammingLanguageNetworkDataSource).programmingLanguages.addAll(
            listOf(
                "c",
                "c++",
                "c#",
                "kotlin",
                "java"
            )
        )
        (fakeAppPreferences as FakeAppPreferences).let {
            it.language = "kotlin"
            it.period = "daily"
        }
        composeTestRule.setContent {
            GithubTrendingTheme {
                val navController = rememberAnimatedNavController()
                Surface(color = MaterialTheme.colors.background) {
                    var showBottomBar by remember {
                        mutableStateOf(false)
                    }
                    Scaffold(bottomBar = {
                        if (showBottomBar) {
                            BottomBar(navController = navController)
                        }
                    }) {
                        SetupTestNavGraph(
                            navController = navController,
                            showBottomBar = { showBottomBar = true },
                            hideBottomBar = { showBottomBar = false },
                            openUrl = {}
                        )
                    }
                }
            }
        }
    }

    @Test
    fun test() {
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("TAG")
        composeTestRule.onNodeWithTag(Constants.TestTags.WELCOME_BTN).performClick()
        composeTestRule.onNodeWithText("Trending Repos").assertExists()
    }
}