package ru.hse.glimpse.screens.entrypoint

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.screens.entrypoint.di.EntrypointComponent
import ru.hse.glimpse.screens.entrypoint.presentation.news.EntrypointNews
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import javax.inject.Inject

@AndroidEntryPoint
class EntrypointFragment : Fragment(R.layout.fragment_entrypoint) {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var component: EntrypointComponent

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
        }
    }
}
