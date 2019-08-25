package com.wiseassblog.androidrestcalculator.userinterface

import com.wiseassblog.androidrestcalculator.domain.ResultWrapper

interface ICalculatorUI {

    interface View {
        var display: String
        fun showError(value: String)
    }

    interface Logic {
        fun handleViewEvent(event: ViewEvent)
        fun handleResult(result: ResultWrapper<Exception, String>)
    }
}

const val GENERIC_ERROR_MESSAGE = "An error has occured."

sealed class ViewEvent {
    object Evaluate : ViewEvent()
    object Delete : ViewEvent()
    object DeleteAll: ViewEvent()
    data class Input(val input:String): ViewEvent()
}

