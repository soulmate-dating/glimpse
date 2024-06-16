package ru.hse.glimpse.screens.chats.dialog

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentDialogBinding
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.screens.chats.dialog.DialogFragment.Companion.DialogFragmentArgs
import ru.hse.glimpse.screens.chats.dialog.di.DialogComponent
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogNews
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogUiEvent
import ru.hse.glimpse.utils.views.FlowFragment
import ru.hse.glimpse.utils.views.IArgs
import ru.hse.glimpse.utils.views.IFragmentArgs
import ru.hse.glimpse.utils.views.args
import ru.hse.glimpse.utils.views.setArgs
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel

@AndroidEntryPoint
class DialogFragment : FlowFragment<DialogComponent>(R.layout.fragment_dialog),
    IFragmentArgs<DialogFragmentArgs> {

    companion object {

        fun newInstance(
            companionId: String,
            companionName: String,
            avatarLink: String,
        ): DialogFragment {
            return DialogFragment().setArgs(
                DialogFragmentArgs(
                    companionId,
                    companionName,
                    avatarLink
                )
            )
        }

        @Parcelize
        data class DialogFragmentArgs(
            val companionId: String,
            val companionName: String,
            val avatarLink: String,
        ) : IArgs
    }

    private val args by args()

    private val binding by viewBinding(FragmentDialogBinding::bind)
    private val store by storeViaViewModel { component.createDialogStore() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            lifecycleOwner = this,
            stateCollector = null,
            newsCollector = ::news,
        )

        store.dispatch(DialogUiEvent.OnInit)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAppBar()
    }

    private fun initAppBar() {
        binding.name.text = args.companionName
        binding.avatar.load(args.avatarLink) {
            crossfade(true)
            placeholder(R.drawable.icon_account_circle)
            transformations(CircleCropTransformation())
        }
        binding.backArrow.setOnClickListener { store.dispatch(DialogUiEvent.BackClicked) }
    }

    private fun news(news: DialogNews) {
        when (news) {
            is DialogNews.NavigateBack -> router.backTo(Screens.ChatsScreen())
        }
    }
}
