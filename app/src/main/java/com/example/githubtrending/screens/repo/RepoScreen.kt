package com.example.githubtrending.screens.repo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.framework.utils.Constants
import com.example.githubtrending.R
import com.example.githubtrending.screens.searchrepos.RepoStats
import com.example.githubtrending.ui.theme.getLightBlue
import com.example.githubtrending.ui.theme.getOrange
import com.example.githubtrending.ui.theme.getTeal

@Composable
fun RepoScreen(
    openUrl: (url: String) -> Unit,
    popBackStack: () -> Unit,
    viewModel: RepoViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Scaffold(topBar = { RepoToolbar { popBackStack() } }) {
            if (state.repo != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp, 0.dp, 16.dp, 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = rememberImagePainter(data = state.repo!!.avatar, builder = {
                            transformations(CircleCropTransformation())
                        }),
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp)
                    )
                    Text(text = state.repo!!.author)
                    Spacer(modifier = Modifier.height(36.dp))
                    Row {
                        Text(
                            text = state.repo!!.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        RepoStats(repo = state.repo!!)
                    }
                    Text(text = state.repo!!.description, textAlign = TextAlign.Center)
                    if (!state.isSaved) {
                        Button(
                            onClick = { viewModel.setEvent(RepoScreenEvent.SaveRepo) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 8.dp, 0.dp, 0.dp)
                                .testTag(Constants.TestTags.SAVE_BTN),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = getTeal(),
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Save")
                        }
                    } else {
                        Button(
                            onClick = { viewModel.setEvent(RepoScreenEvent.DeleteRepo) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 8.dp, 0.dp, 0.dp)
                                .testTag(Constants.TestTags.DELETE_BTN),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = getOrange(),
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Delete")
                        }
                    }
                    Button(
                        onClick = {
                            openUrl(state.repo!!.url)
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 8.dp, 0.dp, 0.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = getLightBlue(),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Show repo")
                    }
                }
            }
        }
    }
}

@Composable
fun RepoToolbar(iconCallback: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(16.dp, 8.dp, 8.dp, 8.dp)
                .clickable { iconCallback() }
                .testTag(Constants.TestTags.BACK_BTN),
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
            contentDescription = "back_arrow",
            tint = Color.White
        )
    }
}