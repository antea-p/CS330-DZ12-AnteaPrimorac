package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen.AppLogic
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen.WaterIntake
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appLogic: AppLogic
): ViewModel() {
    private val _intakes = MutableLiveData<List<WaterIntake>>()
    val intakes: MutableLiveData<List<WaterIntake>>
        get() = _intakes

    fun loadIntakes() {
        viewModelScope.launch {
            appLogic.getIntakes().collect { intakes ->
                _intakes.value = intakes
            }
        }
    }

    fun addIntake(intake: WaterIntake) {
        viewModelScope.launch {
            appLogic.insertIntake(intake)
        }
    }

    fun deleteIntake(intake: WaterIntake) {
        viewModelScope.launch {
            appLogic.deleteIntake(intake)
        }
    }

    fun getIntakesByDate(date: LocalDate) {
            viewModelScope.launch {
                appLogic.intakeByDate(date)
            }
    }
}