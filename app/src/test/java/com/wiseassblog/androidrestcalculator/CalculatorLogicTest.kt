package com.wiseassblog.androidrestcalculator

import com.wiseassblog.androidrestcalculator.userinterface.CalculatorLogic
import com.wiseassblog.androidrestcalculator.userinterface.ViewEvent
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CalculatorLogicTest {

    private lateinit var viewFake: CalculatorViewFakeImpl
    private lateinit var calc: CalculatorFakeImpl
    private val dispatcher = Dispatchers.Unconfined

    lateinit var calculatorLogic: CalculatorLogic


    /**
     * On Evaluate:
     * 1. Get current display state from View
     * 2. Give state to calc for evaluation
     * 3. Verify callback invoked on View
     */
    @Test
    fun `On Evaluate event success`() {
        viewFake = CalculatorViewFakeImpl()
        calc = CalculatorFakeImpl()
        calc.succeed = true

        calculatorLogic = CalculatorLogic(
            viewFake,
            calc,
            dispatcher
        )

        calculatorLogic.handleViewEvent(ViewEvent.Evaluate)

        assertTrue(viewFake.getDisplayCalled)
        assertTrue(viewFake.setDisplayCalled)
        assertTrue(calc.succeed)
    }

    @Test
    fun `On Evaluate event exception`() {
        viewFake = CalculatorViewFakeImpl()
        calc = CalculatorFakeImpl()


        calculatorLogic = CalculatorLogic(
            viewFake,
            calc,
            dispatcher
        )

        calculatorLogic.handleViewEvent(ViewEvent.Evaluate)

        assertTrue(viewFake.getDisplayCalled)
        assertTrue(viewFake.showErrorCalled)
        assertFalse(calc.succeed)
    }


    /**
     * On Delete:
     * 1. Tell VM to delete symbol
     */
    @Test
    fun `On Delete event`() {
        viewFake = CalculatorViewFakeImpl()
        calc = CalculatorFakeImpl()
        viewFake.expression = "1"

        calculatorLogic = CalculatorLogic(
            viewFake,
            calc,
            dispatcher
        )

        calculatorLogic.handleViewEvent(ViewEvent.Delete)

        assertTrue(viewFake.getDisplayCalled)
        assertTrue(viewFake.expression == "")
    }

    /**
     * On Delete:
     * 1. Get current display state from VM
     * 2. If length of display state > 0, delete last char
     * 3. Give state back to VM
     */
    @Test
    fun `On Delete All event`() {
        viewFake = CalculatorViewFakeImpl()
        calc = CalculatorFakeImpl()
        viewFake.expression = "1+1"


        calculatorLogic = CalculatorLogic(
            viewFake,
            calc,
            dispatcher
        )

        calculatorLogic.handleViewEvent(ViewEvent.DeleteAll)

        assertTrue(viewFake.expression == "")
    }

    /**
     * On Delete:
     * 1. Get current display state from VM
     * 2. If length of display state > 0, delete last char
     * 3. Give state back to VM
     */
    @Test
    fun `On Input called`() {
        viewFake = CalculatorViewFakeImpl()
        calc = CalculatorFakeImpl()
        viewFake.expression = "1"
        calculatorLogic = CalculatorLogic(
            viewFake,
            calc,
            dispatcher
        )

        calculatorLogic.handleViewEvent(ViewEvent.Input("+"))
        assertTrue(viewFake.getDisplayCalled)
        assertTrue(viewFake.expression == "1+")
    }
}