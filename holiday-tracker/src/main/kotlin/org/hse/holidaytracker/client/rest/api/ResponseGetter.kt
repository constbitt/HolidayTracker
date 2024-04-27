package org.hse.holidaytracker.client.rest.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import org.hse.holidaytracker.client.rest.models.Holidays

class Response {
    fun getHolidaysResponse(apiUrl: String): Holidays {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(apiUrl)
            .build()
        val response = client.newCall(request).execute()
        val jsonString = response.body?.string()
        val gson = Gson()
        return gson.fromJson(jsonString, Holidays::class.java)
    }
}