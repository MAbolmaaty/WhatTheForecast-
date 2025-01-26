package com.emapps.whattheforecast.data.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.weatherapi.com/v1/"

object RetrofitInstance {

    private val retrofitClient by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor(NetworkInterceptor())
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    val forecastApi: ForecastApi by lazy {
        retrofitClient.create(ForecastApi::class.java)
    }

    class NetworkInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val requestBuffer = Buffer()
            request.body?.writeTo(requestBuffer)

            val response = chain.proceed(request)
            val contentType = response.body?.contentType()
            val content = response.body?.string()

            var requestBody = ""
            try {
                if (requestBuffer.size > 0) {
                    requestBody = requestBuffer.readUtf8()
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

            Log.d("NetworkApiInterceptor", "Response Body: ${content.toString()}")
            Log.d(
                "NetworkApiInterceptor", String.format(
                    "ForecastAPI: %s(%s)(%s)",
                    request.url, request.method, response.code.toString()
                )
            )

            val wrappedBody = ResponseBody.create(contentType, content ?: "")
            return response.newBuilder().body(wrappedBody).build()
        }
    }
}