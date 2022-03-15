package com.aksoyhasan.path.data.remote.remoteDataSource

import com.aksoyhasan.path.data.Resource
import com.aksoyhasan.path.data.dto.CharacterComicsResponse
import com.aksoyhasan.path.data.dto.CharactersResponse
import com.aksoyhasan.path.data.dto.SpecificCharacterResponse
import java.util.*

interface MarvelRemoteDataSource {
    suspend fun getCharacters(
        orderBy: String?,
        limit: Int?,
        offset: Int?,
        apikey: String?,
        hash: String?,
        ts: String?
    ): Resource<CharactersResponse>

    suspend fun getSpecificCharacter(
        characterId: Int,
        apikey: String?,
        hash: String?,
        ts: String?
    ): Resource<SpecificCharacterResponse>

    suspend fun getCharacterComics(
        characterId: Int,
        dateRange: String?,
        orderBy: String?,
        limit: Int?,
        apikey: String?,
        hash: String?,
        ts: String?
    ): Resource<CharacterComicsResponse>
}