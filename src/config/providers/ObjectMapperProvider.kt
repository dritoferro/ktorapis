package br.com.tagliaferrodev.ktor.rest.config.providers

import com.fasterxml.jackson.databind.ObjectMapper

object ObjectMapperProvider {

    fun provide(): ObjectMapper {
        val mapper = ObjectMapper()
        return mapper
    }
}