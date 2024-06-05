package ru.hse.glimpse.screens.main.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.hse.glimpse.databinding.FragmentBottomSheetSendReactionBinding
import ru.hse.glimpse.utils.views.setShowProgress

class SendReactionBottomSheetDialog(
    listener: SendReactionBottomSheetDialogListener,
) : BottomSheetDialogFragment() {

    private val binding by lazy { FragmentBottomSheetSendReactionBinding.inflate(layoutInflater) }
    private var bottomSheetDialogListener: SendReactionBottomSheetDialogListener? = null

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
            bottomSheetDialogListener = context as SendReactionBottomSheetDialogListener
        } catch (_: Exception) {
        }
    }

    private fun bindActions() {
        binding.send.setOnClickListener {
            if (binding.comment.editText?.text.isNullOrEmpty()) {
                binding.comment.editText?.error = "Comment can't be empty!"
            } else {
                binding.send.setShowProgress(true)
                bottomSheetDialogListener?.sendReactionClicked(
                    comment = binding.comment.editText?.text.toString(),
                )
            }
        }
    }

    interface SendReactionBottomSheetDialogListener {
        fun sendReactionClicked(comment: String)
    }
}
