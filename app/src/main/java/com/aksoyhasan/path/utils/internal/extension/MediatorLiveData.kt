package com.aksoyhasan.path.utils.internal.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import filterFalseOrNullOrBlank

fun MediatorLiveData<*>.addSources(elements: List<LiveData<*>?>?, block: (List<LiveData<*>?>?) -> Unit) {
    elements?.filterNotNull()?.forEach {
        this.addSource(it) { value ->
            value?.let {
                block(elements.filterNotNull())
            }
        }
    }
}

fun MediatorLiveData<Boolean>.observeAndUpdateStatusBySources(vararg sources: LiveData<*>?) {
    addSources(sources.toList()) { liveDataList ->
        val res = liveDataList?.filterFalseOrNullOrBlank()?.isEmpty() ?: false
        postValue(res)
    }
}
