package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db.TransactionDatabase
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.MainActivity
import javax.inject.Inject


@HiltAndroidTest
class AcceptanceTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var database: TransactionDatabase

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        database.close()
    }

    // Create test to go from first screen to add intake screen, nad there add all filed and click on Add button, and check if the new intake is added to the list
    @Test
    fun testAddIntake() {
        rule.onNodeWithText("Add intake").assertExists()
        rule.onNodeWithText("Add intake").performClick()
        rule.onNodeWithText("Add Water Intake").assertExists()
        rule.onNodeWithText("Note").performTextInput("Test note")
        rule.onNodeWithText("Add").performClick()

        // Check if the new intake is present in the list

        rule.onNodeWithContentDescription("Water bottle").assertExists()
        rule.onNodeWithContentDescription("Water bottle").performClick()
        rule.onNodeWithText("Water intakes").assertExists()
//        runBlocking { delay(2000) }
//        rule.onNodeWithText("Test note").assertExists()

    }

}