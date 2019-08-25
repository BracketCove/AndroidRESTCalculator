package com.wiseassblog.androidrestcalculator.buildlogic

import com.wiseassblog.androidrestcalculator.CalculatorActivity
import com.wiseassblog.androidrestcalculator.data.CalculatorImpl
import com.wiseassblog.androidrestcalculator.data.RequestApiImpl
import com.wiseassblog.androidrestcalculator.userinterface.CalculatorLogic
import com.wiseassblog.androidrestcalculator.userinterface.ICalculatorUI
import kotlinx.coroutines.Dispatchers

object DependencyProvider {
    fun provideLogic(main: CalculatorActivity): ICalculatorUI.Logic {
        return CalculatorLogic(
            main,
            CalculatorImpl(RequestApiImpl),
            Dispatchers.Main
        )
    }
}