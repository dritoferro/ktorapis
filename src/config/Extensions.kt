package br.com.tagliaferrodev.ktor.rest.config

import io.ktor.application.*
import java.util.*

fun ApplicationCall.getProductId() = UUID.fromString(parameters["id"])