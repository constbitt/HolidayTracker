package org.hse.holidaytracker.client.rest.models

data class State(
    val id: Int,
    val abbrev: String,
    val name: String,
    val exception: String?,
    val iso: String
)