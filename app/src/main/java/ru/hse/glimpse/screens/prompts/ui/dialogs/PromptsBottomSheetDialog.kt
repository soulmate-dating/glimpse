package ru.hse.glimpse.screens.prompts.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.hse.glimpse.databinding.FragmentBottomSheetPromptsBinding
import ru.hse.glimpse.network.api.prompts.model.Prompt

class PromptsBottomSheetDialog(listener: PromptsBottomSheetDialogListener) :
    BottomSheetDialogFragment() {

    private val binding by lazy { FragmentBottomSheetPromptsBinding.inflate(layoutInflater) }
    private var bottomSheetDialogListener: PromptsBottomSheetDialogListener? = null

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
        bindActions()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            bottomSheetDialogListener = context as PromptsBottomSheetDialogListener?
        } catch (_: Exception) {  }
    }

    private fun bindActions() {
        binding.cancel.setOnClickListener {
            bottomSheetDialogListener?.cancelClicked()
        }

        binding.save.setOnClickListener {
            if (binding.answer.editText?.text.isNullOrEmpty()) {
                binding.answer.editText?.error = "Answer can't be empty!"
            } else if (binding.question.editText?.text.isNullOrEmpty()) {
                binding.question.editText?.error = "Question can't be empty!"
            } else {
                bottomSheetDialogListener?.sendPromptClicked(
                    Prompt(
                        content = binding.answer.editText?.text.toString(),
                        question = binding.question.editText?.text.toString(),
                        type = Prompt.PromptType.TEXT,
                    )
                )
            }
        }
    }

    interface PromptsBottomSheetDialogListener {
        fun sendPromptClicked(prompt: Prompt)
        fun cancelClicked()
    }
}
