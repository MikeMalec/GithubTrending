package com.example.githubtrending.screens.repo

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.viewModelScope
import com.example.business.domain.utils.Resource
import com.example.business.usecases.DeleteRepo
import com.example.business.usecases.SaveRepo
import com.example.framework.utils.Constants
import com.example.githubtrending.screens.MainActivity
import com.example.githubtrending.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@OptIn(ExperimentalAnimationApi::class)
@HiltViewModel
class RepoViewModel @Inject constructor(
    private val saveRepo: SaveRepo,
    private val deleteRepo: DeleteRepo,
    @Named(Constants.IO_DISPATCHER)
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel<RepoScreenState>() {
    init {
        updateState {
            repo = MainActivity.repo!!
            isSaved = MainActivity.repo!!.cacheId != null
        }
    }

    fun setEvent(event: RepoScreenEvent) {
        when (event) {
            is RepoScreenEvent.SaveRepo -> saveRepo()
            is RepoScreenEvent.DeleteRepo -> deleteRepo()
        }
    }

    private fun saveRepo() {
        viewModelScope.launch(dispatcher) {
            saveRepo.saveRepo(getState().repo!!).let {
                if (it is Resource.Success) {
                    getState().repo!!.cacheId = it.data
                    updateState {
                        isSaved = true
                    }
                }
            }
        }
    }

    private fun deleteRepo() {
        if (getState().repo!!.cacheId != null) {
            viewModelScope.launch(dispatcher) {
                deleteRepo.deleteRepo(getState().repo!!).let {
                    if (it is Resource.Success) {
                        updateState {
                            isSaved = false
                        }
                    }
                }
            }
        }
    }

    override fun getInitState(): RepoScreenState = RepoScreenState()
}