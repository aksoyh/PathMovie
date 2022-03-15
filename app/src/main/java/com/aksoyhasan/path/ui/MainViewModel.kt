package com.aksoyhasan.path.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aksoyhasan.path.base.BaseViewModel
import com.aksoyhasan.path.data.Resource
import com.aksoyhasan.path.data.dto.CharacterComicsResponse
import com.aksoyhasan.path.data.dto.CharactersResponse
import com.aksoyhasan.path.data.dto.SpecificCharacterResponse
import com.aksoyhasan.path.data.repositorySource.MarvelRepositorySource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val marvelRepositorySource: MarvelRepositorySource
) : BaseViewModel() {

    private val charactersDataPrivate = MutableLiveData<Resource<CharactersResponse>>()
    val charactersLiveData: LiveData<Resource<CharactersResponse>> get() = charactersDataPrivate

    private val characterDetailDataPrivate = MutableLiveData<Resource<SpecificCharacterResponse>>()
    val characterDetailLiveData: LiveData<Resource<SpecificCharacterResponse>> get() = characterDetailDataPrivate

    private val characterComicsDataPrivate = MutableLiveData<Resource<CharacterComicsResponse>>()
    val characterComicsLiveData: LiveData<Resource<CharacterComicsResponse>> get() = characterComicsDataPrivate

    fun getCharacters(
        orderBy: String?,
        limit: Int?,
        offset: Int?,
        apikey: String?,
        hash: String?,
        ts: String?) {
        viewModelScope.launch {
            charactersDataPrivate.value = Resource.Loading()
            marvelRepositorySource.getCharacters(orderBy, limit, offset, apikey, hash, ts).collect {
                charactersDataPrivate.value = it
            }
        }
    }

    fun getSpecificCharacter(
        characterId: Int,
        apikey: String?,
        hash: String?,
        ts: String?
    ) {
        viewModelScope.launch {
            characterDetailDataPrivate.value = Resource.Loading()
            marvelRepositorySource.getSpecificCharacter(characterId, apikey, hash, ts).collect {
                characterDetailDataPrivate.value = it
            }
        }
    }

    fun getCharacterComics(
        characterId: Int,
        dateRange: String?,
        orderBy: String?,
        limit: Int?,
        apikey: String?,
        hash: String?,
        ts: String?
    ) {
        viewModelScope.launch {
            characterComicsDataPrivate.value = Resource.Loading()
            marvelRepositorySource.getCharacterComics(characterId, dateRange, orderBy, limit, apikey, hash, ts).collect {
                characterComicsDataPrivate.value = it
            }
        }
    }
}