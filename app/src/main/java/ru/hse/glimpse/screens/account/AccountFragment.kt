package ru.hse.glimpse.screens.account

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentAccountBinding
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.screens.account.di.AccountComponent
import ru.hse.glimpse.screens.account.presentation.AccountNews
import ru.hse.glimpse.screens.account.presentation.AccountNews.OpenChats
import ru.hse.glimpse.screens.account.presentation.AccountNews.OpenInOrUpScreen
import ru.hse.glimpse.screens.account.presentation.AccountNews.OpenMain
import ru.hse.glimpse.screens.account.presentation.AccountNews.OpenProfile
import ru.hse.glimpse.screens.account.presentation.AccountNews.OpenPrompts
import ru.hse.glimpse.screens.account.presentation.AccountNews.ShowError
import ru.hse.glimpse.screens.account.presentation.AccountState
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.ChatsClicked
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.LogoutClicked
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.MainClicked
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.ProfileClicked
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.PromptsClicked
import ru.hse.glimpse.utils.views.FlowFragment
import ru.hse.glimpse.utils.views.showAlert
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel

@AndroidEntryPoint
class AccountFragment : FlowFragment<AccountComponent>(R.layout.fragment_account) {

    private val binding by viewBinding(FragmentAccountBinding::bind)
    private val store by storeViaViewModel { component.createAccountStore() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            lifecycleOwner = this,
            stateCollector = ::render,
            newsCollector = ::news,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initBottomBar()
        initViews()
    }

    private fun render(state: AccountState) {
        binding.name.text = state.firstName ?: ""
        binding.image.load(state.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.icon_account_circle)
            transformations(CircleCropTransformation())
        }
    }

    private fun initToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        with(activity) {
            setSupportActionBar(binding.toolbar)
            setHasOptionsMenu(true)
        }
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.logout -> {
                    store.dispatch(LogoutClicked)
                    true
                }
                else -> false
            }
        }
    }

    private fun initViews() {
        binding.profileCard.setOnClickListener { store.dispatch(ProfileClicked) }
        binding.promptsCard.setOnClickListener { store.dispatch(PromptsClicked) }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu_account, menu)
    }

    private fun initBottomBar() {
        binding.bottomNavigation.selectedItemId = R.id.account

        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.main -> {
                    store.dispatch(MainClicked)
                    true
                }
                R.id.chats -> {
                    store.dispatch(ChatsClicked)
                    true
                }
                else -> false
            }
        }
    }

    private fun news(news: AccountNews) {
        when (news) {
            is OpenMain -> router.replaceScreen(Screens.MainScreen())
            is OpenChats -> router.replaceScreen(Screens.ChatsScreen())
            is OpenProfile -> router.navigateTo(Screens.FillProfileScreen())
            is OpenPrompts -> router.navigateTo(Screens.PromptsScreen())
            is OpenInOrUpScreen -> router.newRootScreen(Screens.InOrUpScreen())
            is ShowError -> showAlert(news.message)
        }
    }
}
