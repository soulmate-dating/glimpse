package ru.hse.glimpse.network.api.prompts

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.hse.glimpse.network.api.prompts.model.Prompt
import ru.hse.glimpse.network.api.prompts.model.PromptsResponse

interface PromptsApi {

    @GET("users/{user_id}/prompts")
    suspend fun getPrompts(
        @Path("user_id") userId: String,
    ): ApiResponse<PromptsResponse>

    @POST("users/{user_id}/prompts/text")
    suspend fun postTextPrompt(
        @Path("user_id") userId: String,
        @Body prompt: List<Prompt>,
    ): ApiResponse<PromptsResponse>
}
