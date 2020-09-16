package br.com.tagliaferrodev.ktor.rest.repository.impl

import br.com.tagliaferrodev.ktor.rest.products.Product
import br.com.tagliaferrodev.ktor.rest.repository.ProductRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.datetime
import org.jetbrains.exposed.sql.insert
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
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID): Product? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun delete(id: UUID) {
        TODO("Not yet implemented")
    }

}