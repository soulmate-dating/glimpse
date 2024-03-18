package ru.hse.glimpse.screens.fill_profile

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentFillProfileBinding
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.screens.fill_profile.di.FillProfileComponent
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileNews
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileUiEvent
import ru.hse.glimpse.utils.views.FlowFragment
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
            stateCollector = null,
            newsCollector = ::handleNews,
        )

        binding.setDate.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

            datePicker.show(parentFragmentManager, "tag")
            datePicker.addOnPositiveButtonClickListener {
                val dateFormat = SimpleDateFormat("MM.dd.yyyy", Locale.US)
                val selectedDate = datePicker.selection

                val formattedDate = dateFormat.format(selectedDate)
                binding.date.editText?.setText(formattedDate)
            }
        }

        binding.saveButton.setOnClickListener {
            store.dispatch(FillProfileUiEvent.SaveClicked)
        }
    }

    private fun handleNews(news: FillProfileNews) {
        when (news) {
            is FillProfileNews.OpenChatsScreen -> router.newRootChain(Screens.ChatsScreen())
        }
    }
}
