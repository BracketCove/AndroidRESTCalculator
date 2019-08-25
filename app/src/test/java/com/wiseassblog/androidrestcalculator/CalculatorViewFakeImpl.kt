package com.wiseassblog.androidrestcalculator

import android.widget.Toast
import com.wiseassblog.androidrestcalculator.userinterface.ICalculatorUI
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorViewFakeImpl : ICalculatorUI.View {

    var setDisplayCalled: Boolean = false
    var getDisplayCalled: Boolean = false
    var showErrorCalled: Boolean = false
    var expression: String = ""

    override var display: String
        get() {
            getDisplayCalled = true
           return expression
        }
        set(value) {
            setDisplayCalled = true
            expression = value
        }

    override fun showError(value: String) {
        showErrorCalled = true
    }

}