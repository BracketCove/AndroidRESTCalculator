package com.wiseassblog.androidrestcalculator.userinterface

import androidx.lifecycle.ViewModel
import com.wiseassblog.androidrestcalculator.domain.ResultWrapper
import kotlin.properties.Delegates

class CalculatorViewModel(override var logic: ICalculatorUI.Logic? = null) : ICalculatorUI.ViewModel, ViewModel() {

    var displayValue: String by Delegates.observable("",
        { prop, old, new ->
            logic?.handleVmUpdate(new)
        }
    )

    override fun deleteSymbol() {
        displayValue = displayValue.dropLast(1)
    }

    override fun deleteAll() {
        displayValue = ""
    }

    override fun getDisplay(): String = displayValue

    override fun updateDisplay(result: ResultWrapper<Exception, String>) {
        when (result) {
            is ResultWrapper.Success -> {
                displayValue = result.value
            }
            is ResultWrapper.Error -> {
                logic?.handleException(result.error)
            }
        }
    }

    override fun appendSymbol(symbol: String) {
        displayValue += symbol
    }
}