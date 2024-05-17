package ru.hse.glimpse.screens.entrypoint

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.screens.entrypoint.di.EntrypointComponent
import ru.hse.glimpse.screens.entrypoint.presentation.news.EntrypointNews
import ru.hse.glimpse.utils.views.FlowFragment
import ru.hse.glimpse.utils.views.showAlert
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel

@AndroidEntryPoint
class EntrypointFragment : FlowFragment<EntrypointComponent>(R.layout.fragment_entrypoint) {

    private val store by storeViaViewModel { component.getEntrypointStore() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        store.collectOnCreate(
            lifecycleOwner = viewLifecycleOwner,
            stateCollector = null,
            newsCollector = ::handleNews,
        )
    }

    private fun handleNews(news: EntrypointNews) {
        when (news) {
            is EntrypointNews.OpenInOrUpScreen -> router.newRootChain(Screens.InOrUpScreen())
            is EntrypointNews.ShowError -> showAlert(news.message)
            is EntrypointNews.OpenMainScreen -> router.newRootChain(Screens.MainScreen())
            is EntrypointNews.OpenPromptsScreen -> router.newRootChain(Screens.PromptsScreen())
            is EntrypointNews.OpenFillProfileScreen -> router.newRootChain(Screens.FillProfileScreen())
        }
    }
}
