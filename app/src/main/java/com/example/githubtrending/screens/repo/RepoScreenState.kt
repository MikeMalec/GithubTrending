package com.example.githubtrending.screens.repo

import com.example.business.domain.Repo
import com.example.githubtrending.utils.CloneableState

data class RepoScreenState(
    var repo: Repo? = null,
    var isSaved: Boolean = false
) : CloneableState<RepoScreenState> {
    override fun clone(): RepoScreenState {
        return this.copy()
    }
}