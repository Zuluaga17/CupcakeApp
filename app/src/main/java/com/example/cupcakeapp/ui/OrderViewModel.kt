package com.example.cupcakeapp.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

private const val PRICE_PER_CUPCAKE = 2.00

data class OrderUiState(
    val quantity: Int = 0,
    val flavor: String = "",
    val price: String = ""
)

class OrderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun setQuantity(numberCupcakes: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                quantity = numberCupcakes,
                price = calculatePrice(quantity = numberCupcakes)
            )
        }
    }

    fun setFlavor(desiredFlavor: String) {
        _uiState.update { currentState ->
            currentState.copy(flavor = desiredFlavor)
        }
    }

    fun resetOrder() {
        _uiState.value = OrderUiState()
    }

    private fun calculatePrice(quantity: Int = _uiState.value.quantity): String {
        val calculatedPrice = quantity * PRICE_PER_CUPCAKE
        return NumberFormat.getCurrencyInstance().format(calculatedPrice)
    }
}