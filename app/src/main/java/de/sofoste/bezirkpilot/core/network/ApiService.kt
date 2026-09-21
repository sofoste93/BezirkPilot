package de.sofoste.bezirkpilot.core.network

import de.sofoste.bezirkpilot.data.remote.dto.BasicResponse
import de.sofoste.bezirkpilot.data.remote.dto.ChangePasswordRequest
import de.sofoste.bezirkpilot.data.remote.dto.CreateRecipientRequest
import de.sofoste.bezirkpilot.data.remote.dto.DistrictsResponse
import de.sofoste.bezirkpilot.data.remote.dto.DuplicateStreetsResponse
import de.sofoste.bezirkpilot.data.remote.dto.LoginRequest
import de.sofoste.bezirkpilot.data.remote.dto.LoginResponse
import de.sofoste.bezirkpilot.data.remote.dto.RecipientHistoryResponse
import de.sofoste.bezirkpilot.data.remote.dto.RecipientResponse
import de.sofoste.bezirkpilot.data.remote.dto.RecipientsResponse
import de.sofoste.bezirkpilot.data.remote.dto.UpdateRecipientRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login") suspend fun login(@Body request: LoginRequest): LoginResponse
    @POST("auth/logout") suspend fun logout(): BasicResponse
    @POST("auth/change-password") suspend fun changePassword(@Body request: ChangePasswordRequest): BasicResponse
    @GET("districts") suspend fun getDistricts(): DistrictsResponse
    @GET("recipients") suspend fun getRecipients(): RecipientsResponse
    @GET("recipients/search") suspend fun searchRecipients(@Query("q") query: String): RecipientsResponse
    @GET("recipients/{id}") suspend fun getRecipientById(@Path("id") id: Int): RecipientResponse
    @GET("recipients/{id}/history") suspend fun getRecipientHistory(@Path("id") id: Int): RecipientHistoryResponse
    @POST("recipients") suspend fun createRecipient(@Body request: CreateRecipientRequest): RecipientResponse
    @PUT("recipients/{id}") suspend fun updateRecipient(@Path("id") id: Int, @Body request: UpdateRecipientRequest): RecipientResponse
    @GET("streets/duplicates") suspend fun getDuplicateStreets(): DuplicateStreetsResponse
}