package com.wiseassblog.androidrestcalculator.userinterface

import com.wiseassblog.androidrestcalculator.domain.ResultWrapper

interface ICalculatorUI {

    interface View {
        fun getCurrentExpression(): String
        fun setDisplay(value: String)
        fun showError(value: String)
    }

    interface ViewModel {
        fun getDisplay(): String
        fun appendSymbol(symbol:String)
        fun deleteSymbol()
        fun deleteAll()
        fun updateDisplay(result: ResultWrapper<Exception, String>)
        var logic: ICalculatorUI.Logic?
    }

    interface Logic {
        fun handleViewEvent(event: ViewEvent)
        fun handleVmUpdate(display: String)
        fun handleException(exception: Exception)
    }
}

const val GENERIC_ERROR_MESSAGE = "An error has occured."

sealed class ViewEvent {
    object Bind : ViewEvent()
    object Evaluate : ViewEvent()
    object Delete : ViewEvent()
    object DeleteAll: ViewEvent()
    data class Input(val input:String): ViewEvent()
}

