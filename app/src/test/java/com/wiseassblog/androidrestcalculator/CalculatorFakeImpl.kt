package com.wiseassblog.androidrestcalculator

import com.wiseassblog.androidrestcalculator.domain.ICalculator
import com.wiseassblog.androidrestcalculator.domain.ResultWrapper

class CalculatorFakeImpl : ICalculator {

    var succeed = false

    override suspend fun evaluateExpression(exp: String, callback: (ResultWrapper<Exception, String>) -> Unit) {
        if (succeed) callback.invoke(ResultWrapper.build { "4" })
        else callback.invoke(ResultWrapper.build { throw java.lang.Exception() })
    }

}