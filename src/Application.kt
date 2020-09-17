package br.com.tagliaferrodev.ktor.rest

import br.com.tagliaferrodev.ktor.rest.config.configModule
import br.com.tagliaferrodev.ktor.rest.products.product
import br.com.tagliaferrodev.ktor.rest.products.productModule
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.koin.core.logger.PrintLogger
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.get
import javax.sql.DataSource

fun main() {
    val port = (System.getenv("SERVER_PORT") ?: "9090").toInt()
    MainApp.start(port)
}

object MainApp {

    fun start(port: Int) = embeddedServer(
        factory = Netty,
        port = port,
        module = Application::module,
        watchPaths = listOf("br.com.tagliaferrodev.ktor.rest")
    ).start(wait = true)
}

fun Application.module() {
    install(Koin) {
        val modules = mutableListOf(
            configModule,
            productModule
        )
        modules(modules)
        logger(PrintLogger())
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        header(HttpHeaders.Authorization)
//        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(Authentication) {
    }

    install(ContentNegotiation) {
        register(ContentType.Application.Json, JacksonConverter(get()))
    }

    install(StatusPages) {
        exception<AuthenticationException> { cause ->
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> { cause ->
            call.respond(HttpStatusCode.Forbidden)
        }

    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }

        product(get())
    }

    environment.monitor.subscribe(ApplicationStarted) {
        startDb(get())
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

private fun startDb(dataSource: DataSource) {
    Flyway.configure().dataSource(dataSource).load().migrate()
    Database.connect(dataSource)
}