package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.navigation.NavSetup
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.theme.CS330DZ12AnteaPrimorac5157

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CS330DZ12AnteaPrimorac5157 {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavSetup()
                }
            }
        }
    }
}