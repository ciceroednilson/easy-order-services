package br.com.ciceroednilson.repository.mysql

import br.com.ciceroednilson.repository.OrderEntity
import br.com.ciceroednilson.repository.query.QueryMySQL
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.time.LocalDateTime


@Repository
class MysqlOrderRepository(
    @Autowired
    @Qualifier("jdbcTemplateMysql")
    var jdbcTemplate: JdbcTemplate
) {

    fun findOrders(): List<OrderEntity> {
        val orders = mutableListOf<OrderEntity>()
        val list = this.jdbcTemplate.queryForList(QueryMySQL.builder().findAllOrders)
        list.forEach {
            orders.add(
                OrderEntity(
                    idOrder = it["idOrder"].toString().toLong(),
                    codeProduct = it["codeProduct"].toString().toLong(),
                    descProduct = it["descProduct"].toString(),
                    valueProduct = it["valueProduct"].toString().toBigDecimal(),
                    codeCategory = it["codeCategory"].toString().toLong(),
                    descCategory = it["descCategory"].toString(),
                    dateCreated = LocalDateTime.parse(it["dateCreated"].toString()),
                    flagMigrated = it["flagMigrated"].toString().toBoolean(),
                    dateMigrated = LocalDateTime.now(),
                )
            )
        }
        return orders;
    }

    fun update(idProduct: Long, idOrder: Long) {
        jdbcTemplate.update(QueryMySQL.builder().updateOrderXProduct, idProduct, idOrder)
    }

}