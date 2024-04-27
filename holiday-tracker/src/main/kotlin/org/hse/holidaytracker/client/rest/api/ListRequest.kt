package org.hse.holidaytracker.client.rest.api

sealed class HolidayListRequest {
    data class ByCountryAndYear(val apiKey: String, val country: String, val year: String) : HolidayListRequest()
    data class ByDate(val apiKey: String, val country: String, val year: String, val month: String, val day: String) : HolidayListRequest()
    data class ByType(val apiKey: String, val country: String, val year: String, val month: String? = null, val day: String? = null, val type: String) : HolidayListRequest()
    data class UpcomingHolidays(val apiKey: String, val country: String) : HolidayListRequest()
}