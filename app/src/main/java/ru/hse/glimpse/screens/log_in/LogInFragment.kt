package ru.hse.glimpse.screens.log_in

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentLogInBinding
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.screens.log_in.di.LogInComponent
import ru.hse.glimpse.screens.log_in.presentation.LogInEvent
import ru.hse.glimpse.screens.log_in.presentation.LogInEvent.LoginClicked
import ru.hse.glimpse.screens.log_in.presentation.LogInNews
import ru.hse.glimpse.utils.views.FlowFragment
import ru.hse.glimpse.utils.views.makeTextLink
import ru.hse.glimpse.utils.views.showAlert
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel

@AndroidEntryPoint
class LogInFragment : FlowFragment<LogInComponent>(R.layout.fragment_log_in) {

    private val binding by viewBinding(FragmentLogInBinding::bind)

    private val store by storeViaViewModel { component.createLogInStore() }

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
        makeTextLink(
            textView = binding.alreadyHaveAnAccount,
            string = getString(R.string.sign_up),
            action = { store.dispatch(LogInEvent.SignUpClicked) },
        )

        binding.login.setOnClickListener {
            if (binding.email.editText?.text.isNullOrEmpty()) {
                binding.email.editText?.error = "Email can't be empty!"
            }
            if (binding.password.editText?.text.isNullOrEmpty()) {
                binding.password.editText?.error = "Password can't be empty!"
            }

            if (!binding.email.editText?.text.isNullOrEmpty() &&
                !binding.password.editText?.text.isNullOrEmpty()
            ) {
                store.dispatch(
                    LoginClicked(
                        email = binding.email.editText?.text?.toString()!!,
                        password = binding.password.editText?.text?.toString()!!,
                    )
                )
            }
        }
    }


    private fun handleNews(news: LogInNews) {
        when (news) {
            is LogInNews.OpenSignUp -> router.replaceScreen(Screens.SignUpScreen())
            is LogInNews.OpenMain -> router.newRootChain(Screens.MainScreen())
            is LogInNews.ShowError -> showAlert(news.message)
        }
    }
}
