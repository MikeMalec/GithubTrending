package com.example.githubtrending.screens.searchrepos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.business.domain.Period
import com.example.business.domain.Repo
import com.example.framework.utils.Constants
import com.example.githubtrending.R
import com.example.githubtrending.screens.components.GenericToolbar
import com.example.githubtrending.screens.components.NoContent
import com.example.githubtrending.screens.searchrepos.SearchReposScreenEvent.SearchRepos
import com.example.githubtrending.ui.theme.parse

@Composable
fun SearchReposScreen(
    viewModel: SearchReposViewModel = hiltViewModel(),
    navigateToRepo: (repo: Repo) -> Unit
) {
    val state by viewModel.state.collectAsState()
    var showFilterDialog by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = state.period, key2 = state.language) {
        viewModel.setEvent(SearchRepos)
    }
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 65.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            GenericToolbar(title = "Trending Repos", showFilter = true) {
                showFilterDialog = true
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                state.repos?.let { repos ->
                    if (repos.isNotEmpty()) {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            itemsIndexed(repos) { _, repo ->
                                RepoItem(repo = repo) {
                                    navigateToRepo(repo)
                                }
                            }
                        }
                    } else {
                        NoContent()
                    }
                }
                if (state.loading) {
                    CircularProgressIndicator()
                }
                if (showFilterDialog) {
                    FilterDialog(state,
                        { period, language ->
                            viewModel.setEvent(SearchReposScreenEvent.SetFilters(period, language))
                        }) {
                        showFilterDialog = false
                    }
                }
            }
        }
    }
}


@Composable
fun RepoItem(repo: Repo, showRepo: (repo: Repo) -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .clickable {
                showRepo(repo)
            }
            .testTag("LIST_INDEX_${repo.cacheId.toString()}"),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = rememberImagePainter(
                            data = repo.avatar,
                            builder = {
                                transformations(CircleCropTransformation())
                                placeholder(R.drawable.ghico)
                                error(R.drawable.ghico)
                            },
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                    )
                    Text(text = repo.author, modifier = Modifier.padding(8.dp))
                }
                Row {
                    RepoStats(repo = repo)
                }
            }
            Text(text = repo.name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            Text(modifier = Modifier.testTag(Constants.TestTags.REPO_DESC), text = repo.description)
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    Color.parse(repo.langColor),
                    shape = RoundedCornerShape(8.dp)
                )
        )
    }
}


@Composable
fun FilterDialog(
    state: SearchReposScreenState,
    setFilters: (period: Period, language: String) -> Unit,
    closeDialog: () -> Unit
) {
    var period by remember {
        mutableStateOf(state.period)
    }
    var pickedLanguage by remember {
        mutableStateOf(state.language)
    }
    var showLanguages by remember {
        mutableStateOf(false)
    }
    Dialog(onDismissRequest = { closeDialog() }) {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
                .padding(8.dp), shape = RoundedCornerShape(5.dp), color = Color.White
        ) {
            Column(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp)) {
                if (showLanguages) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(state.languages) { _, language ->
                            Text(text = language, modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .clickable {
                                    pickedLanguage = language
                                    showLanguages = false
                                }
                                .testTag(language)
                            )
                        }
                    }
                } else {
                    Text(text = "Pick a period", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Row(modifier = Modifier.padding(6.dp)) {
                        RadioButton(selected = period.value == "daily", onClick = {
                            period = Period.Daily
                        })
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Daily",
                            modifier = Modifier.clickable { period = Period.Daily })
                    }
                    Row(modifier = Modifier.padding(6.dp)) {
                        RadioButton(selected = period.value == "weekly", onClick = {
                            period = Period.Weekly
                        })
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Weekly", modifier = Modifier.clickable {
                            period = Period.Weekly
                        })
                    }
                    Row(modifier = Modifier.padding(6.dp)) {
                        RadioButton(selected = period.value == "monthly", onClick = {
                            period = Period.Monthly
                        })
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Monthly", modifier = Modifier.clickable {
                            period = Period.Monthly
                        })
                    }
                    Text(text = "Pick a language", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = pickedLanguage, modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .clickable {
                            showLanguages = true
                        }
                        .testTag(Constants.TestTags.PICK_LANGUAGE)
                    )
                }
            }
            if (!showLanguages) {
                Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.BottomEnd) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            modifier = Modifier.testTag(Constants.TestTags.ACCEPT_FILTER),
                            onClick = {
                                setFilters(period, pickedLanguage)
                                closeDialog()
                            }, shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(text = "Accept")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { closeDialog() }, shape = RoundedCornerShape(16.dp)) {
                            Text(text = "Cancel")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun RepoStats(repo: Repo) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.stars),
            contentDescription = "stars",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(25.dp, 25.dp)
        )
        Text(text = repo.stars.toString(), fontSize = 8.sp)
    }
    Spacer(modifier = Modifier.width(8.dp))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.codefork),
            contentDescription = "forks",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(25.dp, 25.dp)
        )
        Text(text = repo.forks.toString(), fontSize = 8.sp)
    }
}