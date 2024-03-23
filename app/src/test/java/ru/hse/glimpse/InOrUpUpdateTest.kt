package ru.hse.glimpse

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldBeSingleton
import io.kotest.matchers.types.shouldBeInstanceOf
import ru.hse.glimpse.screens.in_or_up.presentation.InOrUpEvent
import ru.hse.glimpse.screens.in_or_up.presentation.InOrUpNews
import ru.hse.glimpse.screens.in_or_up.presentation.InOrUpState
import ru.hse.glimpse.screens.in_or_up.presentation.InOrUpUpdate

class InOrUpUpdateTest : BehaviorSpec({
    Given("InOrUp screen") {
        val update = InOrUpUpdate()

        When("User clicks on \"Sign Up\" button") {
            val actualNext = update.update(
                state = InOrUpState(),
                event = InOrUpEvent.SingUpClicked
            )

            Then("Opens \"Sign Up\" screen") {
                actualNext.news
                    .shouldBeSingleton()
                    .first()
                    .shouldBeInstanceOf<InOrUpNews.OpenSignUp>()
            }
        }

        When("User clicks on \"Sign In\" button") {
            val actualNext = update.update(
                state = InOrUpState(),
                event = InOrUpEvent.SignInClicked
            )

            Then("Opens \"Sign In\" screen") {
                actualNext.news
                    .shouldBeSingleton()
                    .first()
                    .shouldBeInstanceOf<InOrUpNews.OpenSignUp>()
            }
        }
    }
})
