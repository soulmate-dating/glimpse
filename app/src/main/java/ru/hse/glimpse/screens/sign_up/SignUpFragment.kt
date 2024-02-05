package ru.hse.glimpse.screens.sign_up

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentSignUpBinding
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.screens.sign_up.di.SignUpComponent
import ru.hse.glimpse.screens.sign_up.presentation.SignUpEvent
import ru.hse.glimpse.screens.sign_up.presentation.SignUpNews
import ru.hse.glimpse.utils.views.FlowFragment
import ru.hse.glimpse.utils.views.makeTextLink
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel

@AndroidEntryPoint
class SignUpFragment : FlowFragment<SignUpComponent>(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)

    private val store by storeViaViewModel { component.createSignUpStore() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        store.collectOnCreate(
            lifecycleOwner = viewLifecycleOwner,
            stateCollector = null,
            newsCollector = ::handleNews,
        )
        makeTextLink(
            textView = binding.alreadyHaveAnAccount,
            string = getString(R.string.log_in),
            action = { store.dispatch(SignUpEvent.LogInClicked) },
        )
    }

    private fun handleNews(news: SignUpNews) {
        when (news) {
            is SignUpNews.OpenLogIn -> router.replaceScreen(Screens.LogInScreen())
        }
    }
}
