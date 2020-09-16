package br.com.tagliaferrodev.ktor.rest.repository

import br.com.tagliaferrodev.ktor.rest.products.Product
import java.util.*

interface ProductRepository {
    fun save(product: Product)

    fun update(product: Product)

    fun findById(id: UUID): Product?

    fun findAll(): List<Product>

    fun delete(id: UUID)
}