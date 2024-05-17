package ru.hse.glimpse.network.api.prompts

import com.skydoves.sandwich.ApiResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ru.hse.glimpse.network.api.prompts.model.Prompt
import ru.hse.glimpse.network.api.prompts.model.PromptResponse
import ru.hse.glimpse.network.api.prompts.model.PromptsResponse

class PromptsRepository(
    private val promptsApi: PromptsApi,
) {

    suspend fun getPrompts(
        userId: String,
    ): ApiResponse<PromptsResponse> {
        return promptsApi.getPrompts(userId)
    }

    suspend fun postTextPrompt(
        userId: String,
        prompt: Prompt,
    ): ApiResponse<PromptResponse> {
        return promptsApi.postTextPrompt(userId, prompt)
    }

    suspend fun postImagePrompt(
        userId: String,
        question: RequestBody,
        type: RequestBody,
        file: MultipartBody.Part,
    ): ApiResponse<PromptResponse> {
        return promptsApi.postImagePrompt(userId, question, type, file)
    }
}
