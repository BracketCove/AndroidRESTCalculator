package com.wiseassblog.androidrestcalculator

import com.wiseassblog.androidrestcalculator.domain.ResultWrapper
import com.wiseassblog.androidrestcalculator.userinterface.ICalculatorUI

class CalculatorViewModelFake(
    var resultWrapper: ResultWrapper<Exception, String>? = null
) : ICalculatorUI.ViewModel {
    var getDisplayCalled = false
    var deleteSymbolCalled = false
    var deleteAllCalled = false
    var appendSymbolCalled = false
    var setLogicCalled = false

    override var logic: ICalculatorUI.Logic? = null

    override fun deleteSymbol() {
        deleteSymbolCalled = true
    }

    override fun deleteAll() {
        deleteAllCalled = true
    }

    override fun getDisplay(): String {
        getDisplayCalled = true
        return "2+2"
    }

    override fun appendSymbol(symbol: String) {
        appendSymbolCalled = true
    }

    override fun updateDisplay(result: ResultWrapper<Exception, String>) {
        this.resultWrapper = result
    }


}