package ru.hse.glimpse.network.api.prompts

import com.skydoves.sandwich.ApiResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import ru.hse.glimpse.network.api.prompts.model.Prompt
import ru.hse.glimpse.network.api.prompts.model.PromptResponse
import ru.hse.glimpse.network.api.prompts.model.PromptsResponse

interface PromptsApi {

    @GET("users/{user_id}/prompts")
    suspend fun getPrompts(
        @Path("user_id") userId: String,
    ): ApiResponse<PromptsResponse>

    @POST("users/{user_id}/prompts/text")
    suspend fun postTextPrompt(
        @Path("user_id") userId: String,
        @Body prompt: Prompt,
    ): ApiResponse<PromptResponse>

    @Multipart
    @POST("users/{user_id}/prompts/file")
    suspend fun postImagePrompt(
        @Path("user_id") userId: String,
        @Part("question") question: RequestBody,
        @Part("type") type: RequestBody,
        @Part file: MultipartBody.Part,
    ): ApiResponse<PromptResponse>
}
