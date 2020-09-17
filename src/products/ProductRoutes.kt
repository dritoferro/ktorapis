package br.com.tagliaferrodev.ktor.rest.products

import io.ktor.application.*
import io.ktor.routing.*

fun Routing.product(
    productController: ProductController
) {
    route("/products") {
        post { productController.save(call) }

        put { productController.update(call) }

        get("/{id}") { productController.findById(call) }

        get { productController.findAll(call) }

        delete("/{id}") { productController.delete(call) }
    }
}