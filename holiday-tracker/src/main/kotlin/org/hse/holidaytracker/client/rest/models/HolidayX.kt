package org.hse.holidaytracker.client.rest.models

data class HolidayX(
    val canonical_url: String,
    val country: Country,
    val date: Date,
    val description: String,
    val locations: String,
    val name: String,
    val primary_type: String,
    val states: Any,
    val type: List<String>,
    val urlid: String
)
