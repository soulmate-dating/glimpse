package ru.hse.glimpse.screens.reactions.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.hse.glimpse.databinding.FragmentBottomSheetReplyOnReactionBinding
import ru.hse.glimpse.utils.views.setShowProgress

class ReplyOnReactionBottomSheetDialog(
    listener: ReplyOnReactionBottomSheetDialogListener,
) : BottomSheetDialogFragment() {

    private val binding by lazy { FragmentBottomSheetReplyOnReactionBinding.inflate(layoutInflater) }
    private var bottomSheetDialogListener: ReplyOnReactionBottomSheetDialogListener? = null

    init {
        this.bottomSheetDialogListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            bottomSheetDialogListener = context as ReplyOnReactionBottomSheetDialogListener
        } catch (_: Exception) {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindActions()
    }

    private fun bindActions() {
        binding.send.setOnClickListener {
            if (binding.comment.editText?.text.isNullOrEmpty()) {
                binding.comment.editText?.error = "Comment can't be empty!"
            } else {
                binding.send.setShowProgress(true)
                bottomSheetDialogListener?.onReactionClick(
                    message = binding.comment.editText?.text.toString(),
                )
            }
        }
    }

    interface ReplyOnReactionBottomSheetDialogListener {
        fun onReactionClick(message: String)
    }
}
