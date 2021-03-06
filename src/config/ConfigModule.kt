package br.com.tagliaferrodev.ktor.rest.config

import br.com.tagliaferrodev.ktor.rest.config.providers.DatasourceProvider
import br.com.tagliaferrodev.ktor.rest.config.providers.ObjectMapperProvider
import com.typesafe.config.ConfigFactory
import org.koin.dsl.module
import javax.sql.DataSource

val configModule = module {
    single { EnvironmentConfig(ConfigFactory.load()) }
    single { ObjectMapperProvider.provide() }
    single<DataSource> {
        with(get<EnvironmentConfig>()) {
            DatasourceProvider.provide(dbUrl, dbUser, dbPass)
        }
    }
}