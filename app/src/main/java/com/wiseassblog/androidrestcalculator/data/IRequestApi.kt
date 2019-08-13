package com.wiseassblog.androidrestcalculator.data

import com.wiseassblog.androidrestcalculator.domain.ResultWrapper

//Interface for sending requests to the remote server
interface IRequestApi {
   suspend fun sendRequest(exp: String):ResultWrapper<Exception, String>
}