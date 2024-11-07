package br.com.ciceroednilson.repository.mariadb

import br.com.ciceroednilson.repository.OrderEntity
import br.com.ciceroednilson.repository.query.QueryMariaDB
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class MariadbOrderRepository(
    @Autowired
    @Qualifier("jdbcTemplateMariadb")
    var jdbcTemplate: JdbcTemplate
) {
    fun save(entity: OrderEntity) {
        jdbcTemplate.update(
            QueryMariaDB.builder().insertOrder,
            entity.idOrder,
            entity.codeProduct,
            entity.descProduct,
            entity.valueProduct,
            entity.codeCategory,
            entity.descCategory,
            entity.dateCreated,
            entity.dateMigrated
        )
    }
}