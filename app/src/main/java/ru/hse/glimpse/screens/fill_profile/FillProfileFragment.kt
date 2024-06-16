package ru.hse.glimpse.screens.fill_profile

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentFillProfileBinding
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.screens.fill_profile.di.FillProfileComponent
import ru.hse.glimpse.network.api.profile.model.Profile
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileNews
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileState
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileUiEvent
import ru.hse.glimpse.utils.kotlin.capitalized
import ru.hse.glimpse.utils.views.FlowFragment
import ru.hse.glimpse.utils.views.setShowProgress
import ru.hse.glimpse.utils.views.showAlert
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class FillProfileFragment : FlowFragment<FillProfileComponent>(R.layout.fragment_fill_profile) {

    private val binding by viewBinding(FragmentFillProfileBinding::bind)

    private val store by storeViaViewModel { component.createFillProfileStore() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        store.collectOnCreate(
            lifecycleOwner = viewLifecycleOwner,
            stateCollector = ::render,
            newsCollector = ::handleNews,
        )

        binding.setDate.setOnClickListener { setDateClickListener() }

        binding.saveButton.setOnClickListener {
            if (
                binding.name.editText?.text.isNullOrEmpty() ||
                binding.surname.editText?.text.isNullOrEmpty() ||
                binding.date.editText?.text.isNullOrEmpty() ||
                binding.sex.editText?.text.isNullOrEmpty() ||
                binding.preferredPartner.editText?.text.isNullOrEmpty() ||
                binding.intention.editText?.text.isNullOrEmpty() ||
                binding.height.editText?.text.isNullOrEmpty() ||
                binding.hasChildren.editText?.text.isNullOrEmpty() ||
                binding.familyPlans.editText?.text.isNullOrEmpty() ||
                binding.drinksAlcohol.editText?.text.isNullOrEmpty() ||
                binding.smokes.editText?.text.isNullOrEmpty()
            ) {
                showAlert("Fill whole profile before saving!")
            } else {
                store.dispatch(
                    FillProfileUiEvent.SaveClicked(
                        Profile(
                            userId = "",
                            firstName = binding.name.editText!!.text.toString(),
                            lastName = binding.surname.editText!!.text.toString(),
                            birthDate = binding.date.editText!!.text.toString().lowercase(),
                            sex = binding.sex.editText!!.text.toString().lowercase(),
                            preference = binding.preferredPartner.editText!!.text.toString()
                                .lowercase(),
                            intention = binding.intention.editText!!.text.toString().lowercase(),
                            height = binding.height.editText!!.text.toString().toInt(),
                            hasChildren = binding.hasChildren.editText!!.text.toString()
                                .lowercase() == "yes",
                            familyPlans = binding.familyPlans.editText!!.text.toString()
                                .lowercase(),
                            drinksAlcohol = binding.drinksAlcohol.editText!!.text.toString()
                                .lowercase(),
                            smokes = binding.smokes.editText!!.text.toString().lowercase(),
                        )
                    )
                )
            }
        }
    }

    private fun render(state: FillProfileState) {
        if (state.profile != null) {
            with(state.profile) {
                binding.name.editText?.setText(firstName)
                binding.surname.editText?.setText(lastName)
                binding.date.editText?.setText(birthDate)
                binding.sex.editText?.setText(sex.capitalized())
                binding.preferredPartner.editText?.setText(preference.capitalized())
                binding.intention.editText?.setText(intention.capitalized())
                binding.height.editText?.setText(height.toString())
                binding.hasChildren.editText?.setText(if (hasChildren) "Yes" else "No")
                binding.familyPlans.editText?.setText(familyPlans.capitalized())
                binding.drinksAlcohol.editText?.setText(drinksAlcohol.capitalized())
                binding.smokes.editText?.setText(smokes.capitalized())
            }
        }
        binding.saveButton.setShowProgress(showProgress = state.isLoading)
    }

    private fun handleNews(news: FillProfileNews) {
        when (news) {
            is FillProfileNews.OpenMainScreen -> router.newRootChain(Screens.PromptsScreen())
            is FillProfileNews.ShowError -> showAlert(news.message)
            is FillProfileNews.OpenAccountScreen -> router.replaceScreen(Screens.AccountScreen())
        }
    }

    private fun setDateClickListener() {
        val dateConstraints = CalendarConstraints
            .Builder()
            .setValidator(DateValidatorPointBackward.now())

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setCalendarConstraints(dateConstraints.build())
            .build()

        datePicker.show(parentFragmentManager, "tag")
        datePicker.addOnPositiveButtonClickListener {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val selectedDate = datePicker.selection

            val formattedDate = dateFormat.format(selectedDate)
            binding.date.editText?.setText(formattedDate)
        }
    }
}
