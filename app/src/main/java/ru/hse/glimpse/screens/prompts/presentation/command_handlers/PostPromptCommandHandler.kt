package ru.hse.glimpse.screens.prompts.presentation.command_handlers

import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.core.net.toUri
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.size
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import ru.hse.glimpse.network.api.prompts.PromptsRepository
import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType.IMAGE
import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType.TEXT
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommand
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommand.SendPrompt
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.PostPromptError
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.PostPromptSuccess
import ru.hse.glimpse.screens.prompts.presentation.PromptsEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class PostPromptCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val promptsRepository: PromptsRepository,
    private val context: Context,
) : CommandsFlowHandler<PromptsCommand, PromptsEvent> {

    override fun handle(commands: Flow<PromptsCommand>): Flow<PromptsEvent> {
        return commands.filterIsInstance<SendPrompt>()
            .transform { command ->
                when (command.prompt.type) {
                    TEXT -> {
                        promptsRepository.postTextPrompt(
                            userInfoManager.getUserId() ?: "",
                            command.prompt
                        )
                    }

                    IMAGE -> {
                        val file = File(context.cacheDir, "image.png")
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            context.contentResolver,
                            command.prompt.content.toUri(),
                        )
                        ByteArrayOutputStream().use { bos ->
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)

                            val bitmapData = bos.toByteArray()

                            FileOutputStream(file).use { fos ->
                                fos.write(bitmapData)
                            }
                        }

                        val question = command.prompt
                            .question
                            .toRequestBody("text/plain".toMediaType())
                        val type = "image"
                            .toRequestBody("text/plain".toMediaType())
                        val compressedImageFile =
                            MultipartBody.Part.createFormData(
                                "file",
                                file.name,
                                Compressor.compress(context, file) {
                                    quality(75)
                                    size(2_097_152) // 2 MB
                                }
                                    .asRequestBody("image/*".toMediaType()),
                            )

                        promptsRepository.postImagePrompt(
                            userId = userInfoManager.getUserId() ?: "",
                            question = question,
                            type = type,
                            file = compressedImageFile,
                        )
                    }
                }.suspendOnSuccess {
                    emit(PostPromptSuccess(this.data.data))
                }.suspendOnFailure {
                    emit(PostPromptError(this.message()))
                }
            }
    }
}
