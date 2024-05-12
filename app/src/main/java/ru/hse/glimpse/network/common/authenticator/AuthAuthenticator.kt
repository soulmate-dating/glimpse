package ru.hse.glimpse.network.common.authenticator

import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.isSuccess
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import ru.hse.glimpse.network.api.refresh.RefreshTokenApi
import ru.hse.glimpse.network.common.token.JwtTokenManager
import javax.inject.Inject

private const val HEADER_AUTHORIZATION = "Authorization"
private const val TOKEN_TYPE = "Bearer"

class AuthAuthenticator @Inject constructor(
    private val tokenManager: JwtTokenManager,
    private val refreshTokenApi: RefreshTokenApi,
    // todo: add return to main screen when 401 unauthorized
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val currentToken = runBlocking { tokenManager.getAccessJwt() }

        synchronized(this) {
            val updatedToken = runBlocking { tokenManager.getAccessJwt() }
            val token = if (currentToken != updatedToken) updatedToken else {
                val newSessionResponse = runBlocking { refreshTokenApi.refresh() }
                if (newSessionResponse.isSuccess) {
                    newSessionResponse.getOrNull().let { body ->
                        runBlocking {
                            tokenManager.saveAccessJwt(body?.data?.accessToken ?: "")
                            tokenManager.saveRefreshJwt(body?.data?.refreshToken ?: "")
                        }
                        body?.data?.accessToken
                    }
                } else {
                    null
                }
            }
            return if (token != null) response.request.newBuilder()
                .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
                .build() else null
        }
    }
}
