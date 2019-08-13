package com.wiseassblog.androidrestcalculator.buildlogic

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.wiseassblog.androidrestcalculator.data.CalculatorImpl
import com.wiseassblog.androidrestcalculator.data.RequestApiImpl
import com.wiseassblog.androidrestcalculator.userinterface.CalculatorFragment
import com.wiseassblog.androidrestcalculator.userinterface.CalculatorLogic
import com.wiseassblog.androidrestcalculator.userinterface.CalculatorViewModel
import kotlinx.coroutines.Dispatchers

object DependencyProvider {
    fun provideLogic(main: AppCompatActivity,  view: CalculatorFragment) {

       val logic = CalculatorLogic(
            view,
            ViewModelProviders.of(main).get(CalculatorViewModel::class.java),
            CalculatorImpl(RequestApiImpl),
            Dispatchers.Main
        )

        view.logic = logic
    }
}