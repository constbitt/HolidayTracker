package org.hse.holidaytracker.client.rest.repository

import com.google.gson.Gson
import org.hse.holidaytracker.client.rest.models.Holidays
import java.sql.Connection
import java.sql.DriverManager

class BaseService {
    fun getCachedHolidays(apiUrl: String): Holidays? {
        val sql = "SELECT holidays_data FROM cached_holidays WHERE api_url = ?"
        var holidays: Holidays? = null

        getConnection().use { connection ->
            val statement = connection.prepareStatement(sql)
            statement.setString(1, apiUrl)
            val resultSet = statement.executeQuery()

            if (resultSet.next()) {
                val json = resultSet.getString("holidays_data")
                holidays = Gson().fromJson(json, Holidays::class.java)
            }
        }

        return holidays
    }

    fun saveHolidaysToDatabase(apiUrl: String, holidays: Holidays) {
        val sql = "INSERT INTO cached_holidays (api_url, holidays_data) VALUES (?, ?)"

        getConnection().use { connection ->
            val statement = connection.prepareStatement(sql)
            statement.setString(1, apiUrl)
            statement.setString(2, Gson().toJson(holidays))
            statement.executeUpdate()
        }
    }

    private fun getConnection(): Connection {
        val url = "jdbc:postgresql://localhost:5432/holiday-tracker"
        val user = "postgres"
        val password = "password"
        return DriverManager.getConnection(url, user, password)
    }
}