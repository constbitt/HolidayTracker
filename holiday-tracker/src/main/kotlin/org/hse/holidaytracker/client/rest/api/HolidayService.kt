package org.hse.holidaytracker.client.rest.api

import org.hse.holidaytracker.client.rest.models.Holidays
import org.hse.holidaytracker.client.rest.repository.BaseService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class HolidayService {
    fun getHolidays(request: HolidayListRequest): Holidays {
        val apiUrl = constructApiUrl(request)
        val baseService = BaseService()
        val cachedHolidays = baseService.getCachedHolidays(apiUrl)

        return if (cachedHolidays != null) {
            cachedHolidays
        } else {
            val response = Response()
            val newHolidays = response.getHolidaysResponse(apiUrl)
            baseService.saveHolidaysToDatabase(apiUrl, newHolidays)
            newHolidays
        }
    }

    private fun constructApiUrl(request: HolidayListRequest): String {
        return when (request) {
            is HolidayListRequest.ByCountryAndYear -> "https://calendarific.com/api/v2/holidays?api_key=${request.apiKey}&country=${request.country}&year=${request.year}"
            is HolidayListRequest.ByDate -> "https://calendarific.com/api/v2/holidays?api_key=${request.apiKey}&country=${request.country}&year=${request.year}&month=${request.month}&day=${request.day}"
            is HolidayListRequest.ByType -> {
                val dateParams = if (request.month != null && request.day != null) "&month=${request.month}&day=${request.day}" else ""
                "https://calendarific.com/api/v2/holidays?api_key=${request.apiKey}&country=${request.country}&year=${request.year}$dateParams&type=${request.type}"
            }
            is HolidayListRequest.UpcomingHolidays -> {
                val today = LocalDate.now()
                val tomorrow = today.plusDays(1.toLong())
                "https://calendarific.com/api/v2/holidays?api_key=${request.apiKey}&country=${request.country}&year=${tomorrow.year}&month=${tomorrow.monthValue}&day=${tomorrow.dayOfMonth}"
            }
        }
    }

}