package com.example.githubtrending.screens.savedrepos

import androidx.lifecycle.ViewModel
import com.example.business.domain.Repo
import com.example.business.usecases.GetRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SavedReposViewModel @Inject constructor(
    private val getRepos: GetRepos
) : ViewModel() {
    val repos: Flow<List<Repo>>
        get() = getRepos.getRepos()
}