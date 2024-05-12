package ru.hse.glimpse.network.api.prompts

import com.skydoves.sandwich.ApiResponse
import ru.hse.glimpse.network.api.prompts.model.Prompt
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
        prompt: List<Prompt>,
    ): ApiResponse<PromptsResponse> {
        return promptsApi.postTextPrompt(userId, prompt)
    }
}
