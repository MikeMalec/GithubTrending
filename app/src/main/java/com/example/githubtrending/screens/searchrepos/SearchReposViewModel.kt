package com.example.githubtrending.screens.searchrepos

import androidx.lifecycle.viewModelScope
import com.example.business.data.preferences.AppPreferences
import com.example.business.domain.Period
import com.example.business.domain.utils.Resource
import com.example.business.usecases.FetchProgrammingLanguages
import com.example.business.usecases.FetchRepos
import com.example.framework.utils.Constants
import com.example.githubtrending.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SearchReposViewModel @Inject constructor(
    private val fetchRepos: FetchRepos,
    private val fetchProgrammingLanguages: FetchProgrammingLanguages,
    private val preferences: AppPreferences,
    @Named(Constants.IO_DISPATCHER)
    private val dispatcher: CoroutineDispatcher,
) : BaseViewModel<SearchReposScreenState>() {

    private fun getPeriod() = state.value.period
    private fun getLanguage() = state.value.language

    init {
        setEvent(SearchReposScreenEvent.FetchProgrammingLanguages)
        setFiltersFromPrefs()
    }

    private fun setFiltersFromPrefs() {
        viewModelScope.launch {
            val language = preferences.getLanguage()
            val period = preferences.getPeriod()
            updateState {
                this.language = language
                this.period = Period.getByValue(period)
            }
        }
    }

    fun setEvent(event: SearchReposScreenEvent) {
        when (event) {
            is SearchReposScreenEvent.SearchRepos -> searchRepos()
            is SearchReposScreenEvent.FetchProgrammingLanguages -> fetchProgrammingLanguages()
            is SearchReposScreenEvent.SetFilters -> setFilters(event.period, event.language)
        }
    }

    private fun searchRepos() {
        fetchRepos.fetchRepos(
            getPeriod().value,
            getLanguage()
        )
            .onCompletion {
                updateState { loading = false }
            }
            .onEach { response ->
                when (response) {
                    is Resource.Loading -> updateState { loading = true }
                    is Resource.Success -> updateState { repos = response.data }
                    is Resource.Error -> updateState { error = response.error }
                }
            }
            .flowOn(dispatcher)
            .launchIn(viewModelScope)
    }

    private fun fetchProgrammingLanguages() {
        fetchProgrammingLanguages.fetchProgrammingLanguages()
            .onEach {
                if (it is Resource.Success) {
                    updateState {
                        languages = it.data
                    }
                }
            }
            .flowOn(dispatcher)
            .launchIn(viewModelScope)
    }

    private fun setFilters(timePeriod: Period, pickedLanguage: String) {
        viewModelScope.launch {
            preferences.saveLanguage(pickedLanguage)
            preferences.savePeriod(timePeriod.value)
            updateState {
                period = timePeriod
                language = pickedLanguage
            }
        }
    }

    override fun getInitState(): SearchReposScreenState = SearchReposScreenState()
}