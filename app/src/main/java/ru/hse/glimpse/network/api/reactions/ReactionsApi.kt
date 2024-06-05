package ru.hse.glimpse.network.api.reactions

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import ru.hse.glimpse.network.api.reactions.model.Reaction
import ru.hse.glimpse.network.api.reactions.model.ReactionPostBody
import ru.hse.glimpse.network.api.reactions.model.ReactionReplyBody

interface ReactionsApi {

    @POST("users/{user_id}/reactions:send")
    suspend fun postReaction(
        @Path("user_id") userId: String,
        @Body reactionPostBody: ReactionPostBody,
    ): ApiResponse<Unit>

    @GET("users/{user_id}/reactions")
    suspend fun getReactions(
        @Path("user_id") userId: String,
    ): ApiResponse<List<Reaction>>

    @PATCH("users/{user_id}/reactions/{reaction_id}:skip")
    suspend fun skipReaction(
        @Path("user_id") userId: String,
        @Path("reaction_id") reactionId: String,
    ): ApiResponse<Unit>

    @PATCH("users/{user_id}/reactions/{reaction_id}:reply")
    suspend fun replyOnReaction(
        @Path("user_id") userId: String,
        @Path("reaction_id") reactionId: String,
        @Body reactionReplyBody: ReactionReplyBody,
    ): ApiResponse<Unit>
}
