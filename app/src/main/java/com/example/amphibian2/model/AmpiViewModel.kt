package com.example.amphibian2.model

import android.text.Spannable.Factory
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibian2.AmphibiansApp
import com.example.amphibian2.data.AmphibiansRepository
import com.example.amphibian2.network.AmphibiansItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed interface AmpiUiState {
    data class Success(val a : List<AmphibiansItem>) : AmpiUiState
    object Error : AmpiUiState
    object Loading : AmpiUiState
}
class AmpiViewModel(private val amphibiansRepository: AmphibiansRepository):ViewModel() {
    var ampiUiState : AmpiUiState by mutableStateOf(AmpiUiState.Loading)
    //var ampiUiState by mutableStateOf("")

init {
    getAmphibians()
}
    fun tryAgain(){
        ampiUiState = AmpiUiState.Loading
        getAmphibians()
    }
    private fun getAmphibians(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try{

                    println("VVVVVVVVIEEEEEEEW 1")
                    val resultlist = amphibiansRepository.getAmphibians()
                    println("VVVVVVVVIEEEEEEEW 2")

                    ampiUiState = AmpiUiState.Success(a = resultlist)
                    println("VVVVVVVVIEEEEEEEW 3")

                }
                catch (e:Exception){
                    println("ERRRRRRRor ${e.message})")

                    ampiUiState = AmpiUiState.Error

                }
            }

        }
    }
    companion object{
        val Factory :ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApp)
                val amphibiansRepository =application.container.AmphibiansRepository
                AmpiViewModel(amphibiansRepository)
            }
        }
    }
}