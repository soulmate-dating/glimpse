package ru.hse.glimpse.screens.in_or_up

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentInOrUpBinding
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.screens.in_or_up.di.InOrUpComponent
import ru.hse.glimpse.screens.in_or_up.presentation.InOrUpEvent
import ru.hse.glimpse.screens.in_or_up.presentation.InOrUpNews
import ru.hse.glimpse.utils.views.FlowFragment
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel

@AndroidEntryPoint
class InOrUpFragment : FlowFragment<InOrUpComponent>(R.layout.fragment_in_or_up) {

    private val store by storeViaViewModel { component.getInOrUpStore() }
    private val binding by viewBinding(FragmentInOrUpBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        store.collectOnCreate(
            lifecycleOwner = viewLifecycleOwner,
            stateCollector = null,
            newsCollector = ::handleNews,
        )

        initViews()
    }

    private fun initViews() {
        binding.signUp.setOnClickListener { store.dispatch(InOrUpEvent.SingUpClicked) }
    }

    private fun handleNews(news: InOrUpNews) {
        when (news) {
            InOrUpNews.OpenSignUp -> router.navigateTo(Screens.SignUpScreen())
        }
    }
}
