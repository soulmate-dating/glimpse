package ru.hse.glimpse.screens.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
import ru.hse.glimpse.screens.account.presentation.AccountNews.OpenReactions
import ru.hse.glimpse.screens.account.presentation.AccountNews.ShowError
import ru.hse.glimpse.screens.account.presentation.AccountState
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.ChatsClicked
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.LogoutClicked
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.MainClicked
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.ProfileClicked
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.PromptsClicked
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.ReactionsClicked
import ru.hse.glimpse.utils.views.FlowFragment
import ru.hse.glimpse.utils.views.showAlert
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel

@AndroidEntryPoint
class AccountFragment : FlowFragment<AccountComponent>(R.layout.fragment_account) {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private val store by storeViaViewModel { component.createAccountStore() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            lifecycleOwner = this,
            stateCollector = ::render,
            newsCollector = ::news,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        binding.bottomNavigation.selectedItemId = R.id.account
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initBottomBar()
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.main -> {
                    store.dispatch(MainClicked)
                    true
                }
                R.id.chats -> {
                    store.dispatch(ChatsClicked)
                    true
                }
                R.id.liked -> {
                    store.dispatch(ReactionsClicked)
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
            is OpenReactions -> router.navigateTo(Screens.ReactionsScreen())
            is OpenInOrUpScreen -> router.newRootScreen(Screens.InOrUpScreen())
            is ShowError -> showAlert(news.message)
        }
    }
}
