package com.wiseassblog.androidrestcalculator.data

import android.util.Log
import com.wiseassblog.androidrestcalculator.domain.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

private const val API_URL = "https://api.mathjs.org/v4/?expr="

object RequestApiImpl : IRequestApi {
    override suspend fun sendRequest(exp: String): ResultWrapper<Exception, String> = withContext(Dispatchers.IO){
        val url = URL(API_URL + URLEncoder.encode(exp, "UTF-8"))
        val httpCon: HttpURLConnection = url.openConnection() as HttpURLConnection

        return@withContext try {

            Log.d("API", httpCon.responseCode.toString())

            ResultWrapper.build { httpCon.inputStream.bufferedReader().readText() }

        } catch (e: Exception) {
            ResultWrapper.build { throw e }
        } finally {
            httpCon.disconnect()
        }
    }
}

