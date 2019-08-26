package com.wiseassblog.androidrestcalculator.data

import com.wiseassblog.androidrestcalculator.domain.ICalculator
import com.wiseassblog.androidrestcalculator.domain.ResultWrapper

class CalculatorImpl(
    val request: IRequestApi
) : ICalculator {
    override suspend fun evaluateExpression(
        exp: String,
        callback: (ResultWrapper<Exception, String>) -> Unit
    ) {
        callback.invoke(request.sendRequest(exp))
    }
}