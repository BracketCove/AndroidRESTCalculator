package com.wiseassblog.androidrestcalculator.userinterface

import com.wiseassblog.androidrestcalculator.domain.ICalculator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.properties.Delegates

class CalculatorLogic(
    val view: ICalculatorUI.View,
    val vm: ICalculatorUI.ViewModel,
    val calc:ICalculator,
    val dispatcher: CoroutineDispatcher
):ICalculatorUI.Logic, CoroutineScope {

    override fun handleViewEvent(event: ViewEvent) {
        when (event){
            is ViewEvent.Bind -> getViewState()
            is ViewEvent.Evaluate -> evaluateExpression()
            is ViewEvent.Delete -> vm.deleteSymbol()
            is ViewEvent.DeleteAll -> vm.deleteAll()
            is ViewEvent.Input -> vm.appendSymbol(event.input)
        }

    }

    private fun evaluateExpression() = launch {
        calc.evaluateExpression(vm.getDisplay(), vm::updateDisplay)
    }

    private fun getViewState() {
        vm.logic = this
        view.setDisplay(vm.getDisplay())
    }

    override fun handleVmUpdate(display: String) {
        view.setDisplay(display)
    }

    override fun handleException(exception: Exception) {
        view.showError(GENERIC_ERROR_MESSAGE)
    }

    protected val jobTracker: Job = Job()

    override val coroutineContext: CoroutineContext
        get()= dispatcher + jobTracker
}