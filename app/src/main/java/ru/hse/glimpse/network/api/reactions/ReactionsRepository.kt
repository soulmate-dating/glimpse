package ru.hse.glimpse.network.api.reactions

import com.skydoves.sandwich.ApiResponse
import ru.hse.glimpse.network.api.reactions.model.Reaction
import ru.hse.glimpse.network.api.reactions.model.ReactionPostBody
import ru.hse.glimpse.network.api.reactions.model.ReactionReplyBody

class ReactionsRepository(
    private val reactionsApi: ReactionsApi,
) {

    suspend fun postReaction(
        userId: String,
        reactionPostBody: ReactionPostBody,
    ): ApiResponse<Unit> {
        return reactionsApi.postReaction(userId, reactionPostBody)
    }

    suspend fun getReactions(
        userId: String,
    ): ApiResponse<List<Reaction>> {
        return reactionsApi.getReactions(userId)
    }

    suspend fun skipReaction(
        userId: String,
        reactionId: String,
    ): ApiResponse<Unit> {
        return reactionsApi.skipReaction(userId, reactionId)
    }

    suspend fun replyOnReaction(
        userId: String,
        reactionId: String,
        message: String,
    ): ApiResponse<Unit> {
        return reactionsApi.replyOnReaction(userId, reactionId, ReactionReplyBody(message))
    }
}
