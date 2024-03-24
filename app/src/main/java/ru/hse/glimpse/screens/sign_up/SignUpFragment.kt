package ru.hse.glimpse.screens.sign_up

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentSignUpBinding
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.screens.sign_up.di.SignUpComponent
import ru.hse.glimpse.screens.sign_up.presentation.SignUpEvent
import ru.hse.glimpse.screens.sign_up.presentation.SignUpNews
import ru.hse.glimpse.screens.sign_up.presentation.SignUpState
import ru.hse.glimpse.utils.views.FlowFragment
import ru.hse.glimpse.utils.views.makeTextLink
import ru.hse.glimpse.utils.views.setShowProgress
import ru.hse.glimpse.utils.views.showAlert
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
            stateCollector = ::render,
            newsCollector = ::handleNews,
        )
        initViews()
    }

    private fun render(state: SignUpState) {
        (binding.createAccount as MaterialButton).setShowProgress(state.isLoading)
    }

    private fun initViews() {
        makeTextLink(
            textView = binding.alreadyHaveAnAccount,
            string = getString(R.string.log_in),
            action = { store.dispatch(SignUpEvent.LogInClicked) },
        )

        binding.createAccount.setOnClickListener {
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
                    SignUpEvent.CreateAccountClicked(
                        email = binding.email.editText?.text?.toString()!!,
                        password = binding.password.editText?.text?.toString()!!,
                    )
                )
            }
        }
    }

    private fun handleNews(news: SignUpNews) {
        when (news) {
            is SignUpNews.OpenLogIn -> {
                router.replaceScreen(Screens.LogInScreen())
            }
            is SignUpNews.OpenFormFilling -> {
                router.newRootChain(Screens.FillProfileScreen())
            }
            is SignUpNews.ShowError -> showAlert(news.message)
        }
    }
}
