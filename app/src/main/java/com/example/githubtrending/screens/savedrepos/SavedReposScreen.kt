package com.example.githubtrending.screens.savedrepos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.business.domain.Repo
import com.example.githubtrending.screens.components.GenericToolbar
import com.example.githubtrending.screens.components.NoContent
import com.example.githubtrending.screens.searchrepos.RepoItem

@Composable
fun SavedRepos(showRepo: (repo: Repo) -> Unit, viewModel: SavedReposViewModel = hiltViewModel()) {
    Surface(color = MaterialTheme.colors.background) {
        val repos = viewModel.repos.collectAsState(listOf())
        Column(modifier = Modifier.fillMaxSize()) {
            GenericToolbar(title = "Saved Repos")
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(repos.value) { _, repo ->
                        RepoItem(repo = repo, showRepo = showRepo)
                    }
                }
                if (repos.value.isEmpty()) {
                    NoContent()
                }
            }
        }
    }
}
