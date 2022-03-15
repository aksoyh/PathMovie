package com.aksoyhasan.path.data.remote.service

import com.aksoyhasan.path.data.HttpConstants
import com.aksoyhasan.path.data.dto.BaseResponse
import com.aksoyhasan.path.data.dto.CharacterComicsResponse
import com.aksoyhasan.path.data.dto.CharactersResponse
import com.aksoyhasan.path.data.dto.SpecificCharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface MarvelService {

    @GET(HttpConstants.CHARACTERS)
    suspend fun getCharacters(
        @Query("orderBy") orderBy: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("apikey") apikey: String?,
        @Query("hash") hash: String?,
        @Query("ts") ts: String?
    ): Response<BaseResponse<CharactersResponse>>

    @GET(HttpConstants.SPECIFIC_CHARACTER)
    suspend fun getSpecificCharacter(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String?,
        @Query("hash") hash: String?,
        @Query("ts") ts: String?
    ): Response<BaseResponse<SpecificCharacterResponse>>

    @GET(HttpConstants.CHARACTER_S_COMICS)
    suspend fun getCharacterComics(
        @Path("characterId") characterId: Int,
        @Query("dateRange") dateRange: String?,
        @Query("orderBy") orderBy: String?,
        @Query("limit") limit: Int?,
        @Query("apikey") apikey: String?,
        @Query("hash") hash: String?,
        @Query("ts") ts: String?
    ): Response<BaseResponse<CharacterComicsResponse>>

}