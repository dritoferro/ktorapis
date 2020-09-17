package br.com.tagliaferrodev.ktor.rest.products

import br.com.tagliaferrodev.ktor.rest.repository.ProductRepository
import br.com.tagliaferrodev.ktor.rest.repository.impl.ProductRepositoryAdapter
import org.koin.dsl.module

val productModule = module {
    single<ProductRepository> { ProductRepositoryAdapter() }
    single { ProductService(get()) }
    single { ProductController(get()) }
}