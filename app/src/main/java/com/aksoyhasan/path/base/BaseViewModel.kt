package com.aksoyhasan.path.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aksoyhasan.path.useCase.errors.ErrorManager
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var errorManager: ErrorManager

    private val isProgressVisible = MutableLiveData<Boolean>()

    fun getProgressLiveData(): LiveData<Boolean> = isProgressVisible

    fun showProgress() {
        isProgressVisible.postValue(true)
    }

    fun hideProgress() {
        isProgressVisible.postValue(false)
    }

    val isLoading = isProgressVisible

}
