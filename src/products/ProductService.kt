package br.com.tagliaferrodev.ktor.rest.products

import br.com.tagliaferrodev.ktor.rest.repository.ProductRepository
import java.util.*

class ProductService(private val repository: ProductRepository) {

    fun save(product: Product) {
        repository.save(product)
    }

    fun update(product: Product) {
        findById(product.id)

        repository.update(product)
    }

    fun findById(id: UUID): Product {
        return repository.findById(id) ?: throw RuntimeException("")
    }

    fun findAll(): List<Product> {
        return repository.findAll()
    }

    fun delete(id: UUID) {
        findById(id)

        repository.delete(id)
    }
}