package br.com.tagliaferrodev.ktor.rest.products

import br.com.tagliaferrodev.ktor.rest.config.getProductId
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*

class ProductController(private val service: ProductService) {

    suspend fun save(call: ApplicationCall) {
        val product = call.receive(Product::class)

        service.save(product)

        call.respond(HttpStatusCode.Created, product)
    }

    suspend fun update(call: ApplicationCall) {
        val product = call.receive(Product::class)

        service.update(product)

        call.respond(product)
    }

    suspend fun findById(call: ApplicationCall) {
        val id = call.getProductId()

        val product = service.findById(id)

        call.respond(product)
    }

    suspend fun findAll(call: ApplicationCall) {
        val products = service.findAll()

        call.respond(products)
    }

    suspend fun delete(call: ApplicationCall) {
        val id = call.getProductId()

        service.delete(id)

        call.respond(HttpStatusCode.NoContent)
    }
}