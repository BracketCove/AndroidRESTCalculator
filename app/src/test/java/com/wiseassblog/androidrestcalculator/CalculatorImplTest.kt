package com.wiseassblog.androidrestcalculator

import com.wiseassblog.androidrestcalculator.data.CalculatorImpl
import com.wiseassblog.androidrestcalculator.data.IRequestApi
import com.wiseassblog.androidrestcalculator.domain.ResultWrapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CalculatorImplTest {


    private val requestFake = FakeRequestApiImpl()
    private val logicFake = CalculatorLogicFakeImpl()
    private lateinit var calc: CalculatorImpl


    val VALID_EXPRESSION: String = "2+2"
    val VALID_ANSWER: String = "4"
    val INVALID_EXPRESSION: String = "2+b"

    /**
     * 1. Repo tells requestApi to make a request
     * 2. requestApi responds with Success
     * 3. Give resulting JSON String to responseApi
     * 4. responseApi returns successful
     */
    @Test
    fun `On Response Successful`() = runBlocking {
        calc = CalculatorImpl(requestFake)

        calc.evaluateExpression(VALID_EXPRESSION, logicFake::handleResult)

        val result = logicFake.result
        if (result is ResultWrapper.Success){
            assertEquals(result.value, "4" )
        } else {
            assertTrue(false)
        }
    }

    /**
     * 1. Repo tells requestApi to make a request
     * 2. requestApi responds with Success
     * 3. Give resulting JSON String to responseApi
     * 4. responseApi returns successful
     */
    @Test
    fun `On Response exception`() = runBlocking {
        calc = CalculatorImpl(requestFake)
        requestFake.succeed = false
        calc.evaluateExpression(INVALID_EXPRESSION, logicFake::handleResult)

        val result = logicFake.result
        if (result is ResultWrapper.Error){
            assertTrue(true)
        } else {
            assertTrue(false)
        }
    }


}

class FakeRequestApiImpl : IRequestApi {

    internal var succeed: Boolean = true
    internal var jsonResponse: String = "4"

    override suspend fun sendRequest(exp: String): ResultWrapper<Exception, String> {
        if (succeed) return ResultWrapper.build { jsonResponse }
        else return ResultWrapper.build { throw Exception() }
    }

}
