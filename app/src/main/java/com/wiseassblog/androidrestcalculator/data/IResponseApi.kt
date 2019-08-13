package com.wiseassblog.androidrestcalculator.data

import com.wiseassblog.androidrestcalculator.domain.ResultWrapper

//interface for decoding a response
interface IResponseApi {
    fun handleResponse(response: String): ResultWrapper<Exception, String>
}