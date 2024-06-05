package ru.hse.glimpse.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.hse.glimpse.network.api.profile.ProfileApi
import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.network.api.prompts.PromptsApi
import ru.hse.glimpse.network.api.prompts.PromptsRepository
import ru.hse.glimpse.network.api.reactions.ReactionsApi
import ru.hse.glimpse.network.api.reactions.ReactionsRepository
import ru.hse.glimpse.network.api.refresh.RefreshTokenApi
import ru.hse.glimpse.network.api.unauthorized.AuthApi
import ru.hse.glimpse.network.api.unauthorized.AuthRepository
import ru.hse.glimpse.network.common.AuthenticatedClient
import ru.hse.glimpse.network.common.PublicClient
import ru.hse.glimpse.network.common.TokenRefreshClient
import ru.hse.glimpse.network.common.authenticator.AuthAuthenticator
import ru.hse.glimpse.network.common.interceptor.AccessTokenInterceptor
import ru.hse.glimpse.network.common.interceptor.RefreshTokenInterceptor
import ru.hse.glimpse.network.common.token.JwtTokenDataStore
import ru.hse.glimpse.network.common.token.JwtTokenManager
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val USER_PREFERENCES = "user_preferences"
private const val BASE_URL = "http://95.174.89.70/api/v0/"
private const val BASE_URL_GOVNO = "http://95.174.89.70/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJwtTokenManager(
        dataStore: DataStore<Preferences>
    ): JwtTokenManager {
        return JwtTokenDataStore(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun providePreferencesDataStore(
        @ApplicationContext appContext: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }

    @AuthenticatedClient
    @Provides
    @Singleton
    fun provideAccessOkHttpClient(
        accessTokenInterceptor: AccessTokenInterceptor,
        authAuthenticator: AuthAuthenticator
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .protocols(listOf(Protocol.HTTP_1_1))
            .authenticator(authAuthenticator)
            .addInterceptor(accessTokenInterceptor)
            .addInterceptor(loggingInterceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(1, TimeUnit.DAYS)
            .readTimeout(1, TimeUnit.DAYS)
            .writeTimeout(1, TimeUnit.DAYS)
            .callTimeout(1, TimeUnit.DAYS)
            .hostnameVerifier { _, _ -> true }
            .build()
    }

    @TokenRefreshClient
    @Provides
    @Singleton
    fun provideRefreshOkHttpClient(
        refreshTokenInterceptor: RefreshTokenInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(refreshTokenInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @PublicClient
    @Provides
    @Singleton
    fun provideUnauthenticatedOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRefreshTokenApi(
        @TokenRefreshClient okHttpClient: OkHttpClient
    ): RefreshTokenApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RefreshTokenApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthenticationApi(@PublicClient okHttpClient: OkHttpClient): AuthApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileApi(
        @AuthenticatedClient okHttpClient: OkHttpClient,
    ): ProfileApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi,
    ): AuthRepository {
        return AuthRepository(authApi = authApi)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        profileApi: ProfileApi,
    ): ProfileRepository {
        return ProfileRepository(profileApi)
    }

    @Provides
    @Singleton
    fun providePromptsApi(
        @AuthenticatedClient okHttpClient: OkHttpClient,
    ): PromptsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PromptsApi::class.java)
    }

    @Provides
    @Singleton
    fun providePromptsRepository(
        promptsApi: PromptsApi,
    ): PromptsRepository {
        return PromptsRepository(promptsApi)
    }

    @Provides
    @Singleton
    fun provideReactionsApi(
        @AuthenticatedClient okHttpClient: OkHttpClient,
    ): ReactionsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_GOVNO)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ReactionsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideReactionsRepository(
        reactionsApi: ReactionsApi,
    ): ReactionsRepository {
        return ReactionsRepository(reactionsApi)
    }
}
