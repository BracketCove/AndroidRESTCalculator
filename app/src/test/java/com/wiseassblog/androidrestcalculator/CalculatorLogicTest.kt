package com.wiseassblog.androidrestcalculator

import com.wiseassblog.androidrestcalculator.data.CalculatorImpl
import com.wiseassblog.androidrestcalculator.userinterface.CalculatorLogic
import com.wiseassblog.androidrestcalculator.userinterface.ViewEvent
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertTrue
import org.junit.Test
import java.lang.Exception

class CalculatorLogicTest {

    private lateinit var viewFake: CalculatorViewFake
    private lateinit var vmFake: CalculatorViewModelFake
    private lateinit var calc: CalculatorImplFake
    private val dispatcher = Dispatchers.Unconfined

    lateinit var calculatorLogic: CalculatorLogic

    /**
     * On bind, request display value from VM and give to View. Since VM persists through orientation changes, this may or may not be an empty String
     *
     * 1. Request display value from VM
     * 2. Give to View
     */
    @Test
    fun `On bind event`() {
        viewFake = CalculatorViewFake()
        vmFake = CalculatorViewModelFake()
        calc = CalculatorImplFake()

        calculatorLogic = CalculatorLogic(
            viewFake,
            vmFake,
            calc,
            dispatcher
        )

        calculatorLogic.handleViewEvent(ViewEvent.Bind)

        assertTrue(viewFake.setDisplayCalled)
        assertTrue(vmFake.getDisplayCalled)
    }

    /**
     * On Evaluate:
     * 1. Get current display state from VM
     * 2. Give state to calc for evaluation
     * 3. Verify callback invoked on VM
     */
    @Test
    fun `On Evaluate event`() {
        viewFake = CalculatorViewFake()
        vmFake = CalculatorViewModelFake()
        calc = CalculatorImplFake()

        calculatorLogic = CalculatorLogic(
            viewFake,
            vmFake,
            calc,
            dispatcher
        )

        calculatorLogic.handleViewEvent(ViewEvent.Evaluate)

        assertTrue(vmFake.getDisplayCalled)
        assertTrue(vmFake.resultWrapper != null)
    }

    /**
     * On Delete:
     * 1. Tell VM to delete symbol
     */
    @Test
    fun `On Delete event`() {
        viewFake = CalculatorViewFake()
        vmFake = CalculatorViewModelFake()
        calc = CalculatorImplFake()

        calculatorLogic = CalculatorLogic(
            viewFake,
            vmFake,
            calc,
            dispatcher
        )

        calculatorLogic.handleViewEvent(ViewEvent.Delete)

        assertTrue(vmFake.deleteSymbolCalled)
    }

    /**
     * On Delete:
     * 1. Get current display state from VM
     * 2. If length of display state > 0, delete last char
     * 3. Give state back to VM
     */
    @Test
    fun `On Delete All event`() {
        viewFake = CalculatorViewFake()
        vmFake = CalculatorViewModelFake()
        calc = CalculatorImplFake()

        calculatorLogic = CalculatorLogic(
            viewFake,
            vmFake,
            calc,
            dispatcher
        )

        calculatorLogic.handleViewEvent(ViewEvent.DeleteAll)

        assertTrue(vmFake.deleteAllCalled)
    }

    /**
     * On Delete:
     * 1. Get current display state from VM
     * 2. If length of display state > 0, delete last char
     * 3. Give state back to VM
     */
    @Test
    fun `On Input called`() {
        viewFake = CalculatorViewFake()
        vmFake = CalculatorViewModelFake()
        calc = CalculatorImplFake()

        calculatorLogic = CalculatorLogic(
            viewFake,
            vmFake,
            calc,
            dispatcher
        )

        calculatorLogic.handleViewEvent(ViewEvent.Input("+"))

        assertTrue(vmFake.appendSymbolCalled)
    }

    /**
     * Handle VM update:
     * 1. Give update to View
     */
    @Test
    fun `On Handle VM update`() {
        viewFake = CalculatorViewFake()
        vmFake = CalculatorViewModelFake()
        calc = CalculatorImplFake()

        calculatorLogic = CalculatorLogic(
            viewFake,
            vmFake,
            calc,
            dispatcher
        )

        calculatorLogic.handleVmUpdate("2+2")

        assertTrue(viewFake.setDisplayCalled)
    }

    /**
     * Handle VM update:
     * 1. tell View to display Exception
     */
    @Test
    fun `On Handle VM Exception`() {
        viewFake = CalculatorViewFake()
        vmFake = CalculatorViewModelFake()
        calc = CalculatorImplFake()

        calculatorLogic = CalculatorLogic(
            viewFake,
            vmFake,
            calc,
            dispatcher
        )

        calculatorLogic.handleException(Exception("FileNotFoundException"))

        assertTrue(viewFake.showErrorCalled)
    }

}