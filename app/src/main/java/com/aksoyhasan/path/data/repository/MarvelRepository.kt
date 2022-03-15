package com.aksoyhasan.path.data.repository

import com.aksoyhasan.path.data.Resource
import com.aksoyhasan.path.data.dto.CharacterComicsResponse
import com.aksoyhasan.path.data.dto.CharactersResponse
import com.aksoyhasan.path.data.dto.SpecificCharacterResponse
import com.aksoyhasan.path.data.remote.remoteData.MarvelRemoteData
import com.aksoyhasan.path.data.repositorySource.MarvelRepositorySource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MarvelRepository @Inject constructor(
    private val remoteData: MarvelRemoteData,
    private val ioDispatcher: CoroutineContext
) :
    MarvelRepositorySource {
    override suspend fun getCharacters(
        orderBy: String?,
        limit: Int?,
        offset: Int?,
        apikey: String?,
        hash: String?,
        ts: String?
    ): Flow<Resource<CharactersResponse>> {
        return flow {
            emit(remoteData.getCharacters(orderBy, limit, offset, apikey, hash, ts))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getSpecificCharacter(
        characterId: Int,
        apikey: String?,
        hash: String?,
        ts: String?
    ): Flow<Resource<SpecificCharacterResponse>> {
        return flow {
            emit(remoteData.getSpecificCharacter(characterId, apikey, hash, ts))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getCharacterComics(
        characterId: Int,
        dateRange: String?,
        orderBy: String?,
        limit: Int?,
        apikey: String?,
        hash: String?,
        ts: String?
    ): Flow<Resource<CharacterComicsResponse>> {
        return flow {
            emit(remoteData.getCharacterComics(characterId, dateRange, orderBy, limit, apikey, hash, ts))
        }.flowOn(ioDispatcher)
    }
}