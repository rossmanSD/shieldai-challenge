package ai.shield.app.shieldaichallenge

import ai.shield.app.shieldaichallenge.view.MainActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val rule = activityScenarioRule<MainActivity>()



    @Test
    fun verifyTitleOfS1Ep3() {
        onView(withId(R.id.recycler_view))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(2))
            .check(
                matches(
                    Utils.atPosition(2,
                        hasDescendant(
                            withText("S1 Ep3: Lord Snow")
                        )
                    )
                )
            )

        // click on the item in the recycleview to load details
        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )

        // now check details pane
        onView(withId(R.id.description)).check(
            matches(
                    withText("Jon Snow attempts to find his place amongst the Night's Watch. Eddard and his daughters arrive at King's Landing.\n\n")
            )
        )
    }
}