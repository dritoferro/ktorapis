package br.com.tagliaferrodev.ktor.rest.repository.impl

import br.com.tagliaferrodev.ktor.rest.products.Product
import br.com.tagliaferrodev.ktor.rest.repository.ProductRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.`java-time`.datetime
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

internal object ProductTable : Table("products") {

    private const val VARCHAR_MAX = 100

    val id = uuid("id")
    val name = varchar("name", VARCHAR_MAX)
    val value = double("value")
    val description = varchar("description", VARCHAR_MAX).nullable()
    val arrivedAt = datetime("arrived_at").nullable()

    override val primaryKey = PrimaryKey(id)

    fun toProduct(resultRow: ResultRow) = Product(
        id = resultRow[id],
        name = resultRow[name],
        value = resultRow[value],
        description = resultRow[description],
        arrivedAt = resultRow[arrivedAt]
    )
}

class ProductRepositoryAdapter : ProductRepository {
    override fun save(product: Product) {
        transaction {
            ProductTable.insert {
                it[id] = product.id
                it[name] = product.name
                it[value] = product.value
                it[description] = product.description
                it[arrivedAt] = product.arrivedAt
            }
        }
    }

    override fun update(product: Product) {
        val result = transaction {
            ProductTable.update({ ProductTable.id eq product.id }) {
                it[name] = product.name
                it[value] = product.value
                it[description] = product.description
                it[arrivedAt] = product.arrivedAt
            }
        }

        val noAffectedRow = result != 1

        if (noAffectedRow) {
            throw RuntimeException("Cannot update this product: $product")
        }
    }

    override fun findById(id: UUID): Product? {
        return transaction {
            ProductTable.select {
                ProductTable.id eq id
            }.firstOrNull()?.let { ProductTable.toProduct(it) }
        }
    }

    override fun findAll(): List<Product> {
        return transaction {
            ProductTable.selectAll().map { ProductTable.toProduct(it) }
        }
    }

    override fun delete(id: UUID) {
        transaction {
            ProductTable.deleteWhere { ProductTable.id eq id }
        }
    }

}