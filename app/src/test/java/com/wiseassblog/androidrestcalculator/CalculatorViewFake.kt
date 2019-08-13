package com.wiseassblog.androidrestcalculator

import com.wiseassblog.androidrestcalculator.userinterface.ICalculatorUI

class CalculatorViewFake : ICalculatorUI.View {

    var setDisplayCalled: Boolean = false
    var showErrorCalled: Boolean = false
    var expression: String = ""

    override fun getCurrentExpression(): String {
        return expression
    }

    override fun setDisplay(value: String) {
        setDisplayCalled = true
    }

    override fun showError(value: String) {
        showErrorCalled = true
    }

}