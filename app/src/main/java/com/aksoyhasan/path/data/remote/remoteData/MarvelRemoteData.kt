package com.aksoyhasan.path.data.remote.remoteData

import com.aksoyhasan.path.data.Resource
import com.aksoyhasan.path.data.dto.BaseResponse
import com.aksoyhasan.path.data.dto.CharacterComicsResponse
import com.aksoyhasan.path.data.dto.CharactersResponse
import com.aksoyhasan.path.data.dto.SpecificCharacterResponse
import com.aksoyhasan.path.data.error.NETWORK_ERROR
import com.aksoyhasan.path.data.error.NO_INTERNET_CONNECTION
import com.aksoyhasan.path.data.error.SHOW_SERVER_MESSAGE
import com.aksoyhasan.path.data.remote.ServiceGenerator
import com.aksoyhasan.path.data.remote.remoteDataSource.MarvelRemoteDataSource
import com.aksoyhasan.path.data.remote.service.MarvelService
import com.aksoyhasan.path.utils.NetworkConnectivity
import com.aksoyhasan.path.utils.internal.extension.emptyIfNull
import retrofit2.Response
import java.io.IOException
import java.util.*
import javax.inject.Inject

class MarvelRemoteData @Inject
constructor(
    serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) :
    MarvelRemoteDataSource {

    private val marvelService = serviceGenerator.createService(MarvelService::class.java)

    override suspend fun getCharacters(
        orderBy: String?,
        limit: Int?,
        offset: Int?,
        apikey: String?,
        hash: String?,
        ts: String?
    ): Resource<CharactersResponse> {
        try {
            val fetchRequest = marvelService.getCharacters(orderBy, limit, offset, apikey, hash, ts)
            return when (val response = processCall { fetchRequest }) {
                is CharactersResponse -> {
                    Resource.Success(data = response)
                }
                is String -> {
                    Resource.DataError(SHOW_SERVER_MESSAGE, response)
                }
                else -> {
                    Resource.DataError(errorCode = response as Int)
                }
            }
        } catch (e: Exception) {
            return if (networkConnectivity.isConnected())
                Resource.DataError(NETWORK_ERROR)
            else
                Resource.DataError(NO_INTERNET_CONNECTION)
        }
    }

    override suspend fun getSpecificCharacter(
        characterId: Int,
        apikey: String?,
        hash: String?,
        ts: String?
    ): Resource<SpecificCharacterResponse> {
        try {
            val fetchRequest = marvelService.getSpecificCharacter(characterId, apikey, hash, ts)
            return when (val response = processCall { fetchRequest }) {
                is SpecificCharacterResponse -> {
                    Resource.Success(data = response)
                }
                is String -> {
                    Resource.DataError(SHOW_SERVER_MESSAGE, response)
                }
                else -> {
                    Resource.DataError(errorCode = response as Int)
                }
            }
        } catch (e: Exception) {
            return if (networkConnectivity.isConnected())
                Resource.DataError(NETWORK_ERROR)
            else
                Resource.DataError(NO_INTERNET_CONNECTION)
        }
    }

    override suspend fun getCharacterComics(
        characterId: Int,
        dateRange: String?,
        orderBy: String?,
        limit: Int?,
        apikey: String?,
        hash: String?,
        ts: String?
    ): Resource<CharacterComicsResponse> {
        try {
            val fetchRequest = marvelService.getCharacterComics(characterId, dateRange, orderBy, limit, apikey, hash, ts)
            return when (val response = processCall { fetchRequest }) {
                is CharacterComicsResponse -> {
                    Resource.Success(data = response)
                }
                is String -> {
                    Resource.DataError(SHOW_SERVER_MESSAGE, response)
                }
                else -> {
                    Resource.DataError(errorCode = response as Int)
                }
            }
        } catch (e: Exception) {
            return if (networkConnectivity.isConnected())
                Resource.DataError(NETWORK_ERROR)
            else
                Resource.DataError(NO_INTERNET_CONNECTION)
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if ((response.body() as BaseResponse<*>).code == 200) {
                (response.body() as BaseResponse<*>).data
            } else {
                val message =
                    (response.body() as BaseResponse<*>).status.emptyIfNull()
                if (message == "")
                    responseCode
                else
                    message
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}