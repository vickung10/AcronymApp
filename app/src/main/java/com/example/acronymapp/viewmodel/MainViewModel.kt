package com.example.acronymapp.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronymapp.model.AcronymResponse
import com.example.acronymapp.model.Status
import com.example.acronymapp.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repo = Repository()

    val noResultsErrorText = ObservableField("")
    val showNoResultsError = ObservableField(false)
    val showClearTextIcon = ObservableField(false)

    private val _acronymResponse = MutableLiveData<AcronymResponse>()
    val acronymResponse: LiveData<AcronymResponse>
        get() = _acronymResponse


    fun fetchMeaning(acronym: String) {
        noResultsErrorText.set("")
        viewModelScope.launch {
            val response = repo.fetchAcronym(acronym)
            when (response.status) {
                Status.SUCCESS -> _acronymResponse.value = response.value!!
                Status.NO_RESULTS -> {
                    noResultsErrorText.set(response.errorMsg)
                    showNoResultsError.set(true)
                }
                Status.ERROR -> {
                    //NO-OP
                }
            }
        }
    }
}