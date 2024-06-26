package ru.hse.glimpse.screens.common.recycler.items

import android.view.View
import androidx.core.view.isVisible
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemLargeTextPromptBinding
import ru.hse.glimpse.network.api.prompts.model.Prompt
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler.clicks.TiRecyclerClickListener

data class TextPromptItem(
    val question: String?,
    val answer: String,
    val isLikeButtonVisible: Boolean = true,
    val prompt: Prompt,
) : ViewTyped {

    override val uid: String
        get() = question + answer
    override val viewType: Int
        get() = R.layout.item_large_text_prompt
}

class TextPromptViewHolder(
    view: View,
    clicks: TiRecyclerClickListener,
    private val onPromptClicked: (Prompt) -> Unit = {},
) : BaseViewHolder<TextPromptItem>(view, clicks) {

    private val binding = ItemLargeTextPromptBinding.bind(view)

    override fun bind(item: TextPromptItem) {
        binding.textPromptQuestion.isVisible = item.question != null
        item.question.let {
            binding.textPromptQuestion.text = item.question
        }
        binding.textPromptAnswer.text = item.answer

        binding.floatingActionButtonText.isVisible = item.isLikeButtonVisible
        binding.floatingActionButtonText.setOnClickListener { onPromptClicked(item.prompt) }

        with(binding.card) {
            isClickable = false
            isFocusable = false
        }
    }
}
