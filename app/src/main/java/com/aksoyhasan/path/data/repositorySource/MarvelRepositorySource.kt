package com.aksoyhasan.path.data.repositorySource

import com.aksoyhasan.path.data.Resource
import com.aksoyhasan.path.data.dto.CharacterComicsResponse
import com.aksoyhasan.path.data.dto.CharactersResponse
import com.aksoyhasan.path.data.dto.SpecificCharacterResponse
import kotlinx.coroutines.flow.Flow
import java.util.*

interface MarvelRepositorySource {
    suspend fun getCharacters(
        orderBy: String?,
        limit: Int?,
        offset: Int?,
        apikey: String?,
        hash: String?,
        ts: String?
    ): Flow<Resource<CharactersResponse>>

    suspend fun getSpecificCharacter(
        characterId: Int,
        apikey: String?,
        hash: String?,
        ts: String?
    ): Flow<Resource<SpecificCharacterResponse>>

    suspend fun getCharacterComics(
        characterId: Int,
        dateRange: String?,
        orderBy: String?,
        limit: Int?,
        apikey: String?,
        hash: String?,
        ts: String?
    ): Flow<Resource<CharacterComicsResponse>>
}