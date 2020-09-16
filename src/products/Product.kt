package br.com.tagliaferrodev.ktor.rest.products

import java.time.LocalDateTime
import java.util.*

data class Product(
    val id: UUID,
    val name: String,
    val value: Double,
    val description: String? = null,
    val arrivedAt: LocalDateTime? = null
) {
}