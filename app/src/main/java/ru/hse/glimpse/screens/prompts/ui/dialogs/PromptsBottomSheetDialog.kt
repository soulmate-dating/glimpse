package ru.hse.glimpse.screens.prompts.ui.dialogs

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentBottomSheetPromptsBinding
import ru.hse.glimpse.network.api.prompts.model.Prompt
import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType
import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType.IMAGE
import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType.TEXT
import ru.hse.glimpse.utils.views.setShowProgress

class PromptsBottomSheetDialog(
    listener: PromptsBottomSheetDialogListener,
    private val type: PromptType,
) : BottomSheetDialogFragment() {

    private val binding by lazy { FragmentBottomSheetPromptsBinding.inflate(layoutInflater) }
    private var bottomSheetDialogListener: PromptsBottomSheetDialogListener? = null
    private var imageUri: Uri? = null

    private val pickPhoto = registerForActivityResult(PickVisualMedia()) { uri ->
        if (uri != null) {
            imageUri = uri
            binding.loadImage.text = getString(R.string.image_is_loaded)
        } else {
            binding.loadImage.text = getString(R.string.image_is_not_loaded)
        }
    }

    init {
        this.bottomSheetDialogListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUi()
        bindActions()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            bottomSheetDialogListener = context as PromptsBottomSheetDialogListener?
        } catch (_: Exception) {
        }
    }

    private fun bindUi() {
        when (type) {
            TEXT -> {
                binding.answer.isVisible = true
                binding.loadImage.isVisible = false
            }

            IMAGE -> {
                binding.answer.isVisible = false
                binding.loadImage.isVisible = true
            }
        }
    }

    private fun bindActions() {
        binding.save.setOnClickListener {
            when (type) {
                TEXT -> {
                    if (binding.answer.editText?.text.isNullOrEmpty()) {
                        binding.answer.editText?.error = "Answer can't be empty!"
                    } else if (binding.question.editText?.text.isNullOrEmpty()) {
                        binding.question.editText?.error = "Question can't be empty!"
                    } else {
                        binding.save.setShowProgress(true)
                        bottomSheetDialogListener?.sendPromptClicked(
                            Prompt(
                                content = binding.answer.editText?.text.toString(),
                                question = binding.question.editText?.text.toString(),
                                type = TEXT,
                            )
                        )
                    }
                }

                IMAGE -> {
                    if (binding.question.editText?.text.isNullOrEmpty()) {
                        binding.question.editText?.error = "Question can't be empty!"
                    } else if (imageUri == null) {
                        binding.loadImage.text = getString(R.string.you_should_load_image)
                    } else {
                        binding.save.setShowProgress(true)
                        bottomSheetDialogListener?.sendPromptClicked(
                            prompt = Prompt(
                                content = imageUri.toString(),
                                question = binding.question.editText?.text.toString(),
                                type = IMAGE,
                            ),
                        )
                    }
                }
            }
        }

        binding.loadImage.setOnClickListener {
            pickPhoto.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }
    }

    interface PromptsBottomSheetDialogListener {
        fun sendPromptClicked(
            prompt: Prompt,
        )
    }
}
