package com.example.githubtrending.screens.repo

sealed class RepoScreenEvent {
    object DeleteRepo : RepoScreenEvent()
    object SaveRepo : RepoScreenEvent()
}