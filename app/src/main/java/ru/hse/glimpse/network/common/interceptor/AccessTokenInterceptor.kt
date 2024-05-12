package ru.hse.glimpse.network.common.interceptor

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.hse.glimpse.network.common.token.JwtTokenManager
import javax.inject.Inject

private const val HEADER_AUTHORIZATION = "Authorization"
private const val TOKEN_TYPE = "Bearer"

class AccessTokenInterceptor @Inject constructor(
    private val tokenManager: JwtTokenManager,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { tokenManager.getAccessJwt() }

        val request = chain.request().newBuilder()
            .addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")

        return chain.proceed(request.build())
    }
}
