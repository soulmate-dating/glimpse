package ru.hse.glimpse.screens.chats.list_of_chats

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentChatsBinding
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.screens.chats.list_of_chats.di.ChatsComponent
import ru.hse.glimpse.screens.chats.list_of_chats.presentation.ChatsNews
import ru.hse.glimpse.screens.chats.list_of_chats.presentation.ChatsUiEvent
import ru.hse.glimpse.screens.chats.list_of_chats.ui.mapper.ChatsUiState
import ru.hse.glimpse.screens.chats.list_of_chats.ui.recycler.ChatsViewHolderFactory
import ru.hse.glimpse.utils.views.FlowFragment
import ru.hse.glimpse.utils.views.showAlert
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines

@AndroidEntryPoint
class ChatsFragment : FlowFragment<ChatsComponent>(R.layout.fragment_chats) {

    private val binding by viewBinding(FragmentChatsBinding::bind)
    private val store by storeViaViewModel { component.createChatsStore() }
    private lateinit var recycler: TiRecyclerCoroutines<ViewTyped>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            fragment = this,
            uiStateMapper = component.uiStateMapper,
            stateCollector = ::render,
            newsCollector = ::handleNews,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initBottomBar()
        initRecycler()
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu_chats, menu)
    }

    private fun initRecycler() {
        recycler = TiRecyclerCoroutines(
            binding.recycler,
            ChatsViewHolderFactory(
                onChatClickListener = {
                    store.dispatch(
                        ChatsUiEvent.ChatClicked(
                            it.companion.id,
                            it.companion.firstName,
                            it.companion.picLink,
                        )
                    )
                },
            ),
        )
    }

    private fun initToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        with(activity) {
            setSupportActionBar(binding.toolbar)
            setHasOptionsMenu(true)
        }
    }

    private fun initBottomBar() {
        binding.bottomNavigation.selectedItemId = R.id.chats

        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.main -> {
                    store.dispatch(ChatsUiEvent.MainScreenClicked)
                    true
                }

                R.id.account -> {
                    store.dispatch(ChatsUiEvent.AccountClicked)
                    true
                }

                R.id.liked -> {
                    store.dispatch(ChatsUiEvent.ReactionsScreenClicked)
                    true
                }

                else -> false
            }
        }
    }

    private fun controlShimmersVisibility(isLoading: Boolean) {
        if (isLoading) {
            binding.shimmers.startShimmer()
        } else {
            binding.shimmers.apply {
                stopShimmer()
                visibility = View.GONE
            }
            binding.recycler.visibility = View.VISIBLE
        }
    }

    private fun render(state: ChatsUiState) {
        controlShimmersVisibility(isLoading = state.isLoading)
        recycler.setItems(state.items)
    }

    private fun handleNews(news: ChatsNews) {
        when (news) {
            is ChatsNews.OpenMainScreen -> router.newRootScreen(Screens.MainScreen())
            is ChatsNews.OpenAccountScreen -> router.newRootScreen(Screens.AccountScreen())
            is ChatsNews.OpenReactionsScreen -> router.newRootScreen(Screens.ReactionsScreen())
            is ChatsNews.OpenDialogScreen -> router.navigateTo(
                Screens.DialogScreen(
                    news.companionId,
                    news.companionName,
                    news.avatarLink,
                )
            )

            is ChatsNews.ShowError -> showAlert(message = news.message)
        }
    }
}
