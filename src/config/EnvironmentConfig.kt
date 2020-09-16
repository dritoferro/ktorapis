package br.com.tagliaferrodev.ktor.rest.config

import com.typesafe.config.Config

class EnvironmentConfig(
    config: Config
) {
    val dbUrl = "jdbc:postgresql://" + config.getString("database.url")
    val dbUser = config.getString("database.user")
    val dbPass = config.getString("database.pass")
}