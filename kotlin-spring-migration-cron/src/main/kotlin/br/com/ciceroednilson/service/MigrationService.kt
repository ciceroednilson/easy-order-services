package br.com.ciceroednilson.service

import br.com.ciceroednilson.repository.mariadb.MariadbOrderRepository
import br.com.ciceroednilson.repository.mysql.MysqlOrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MigrationService(
    @Autowired var mariadbOrderRepository: MariadbOrderRepository,
    @Autowired var mysqlOrderRepository: MysqlOrderRepository
) {
    fun execute() {
        println("starting...")
        val ordersEntity = mysqlOrderRepository.findOrders()
        if (ordersEntity.isEmpty()) {
            println("Empty data to migration")
            return
        }
        ordersEntity.forEach {
            println(String.format("saving the product %s and order %s", it.codeProduct, it.idOrder))
            mariadbOrderRepository.save(it)
            println(String.format("updating the product %s and order %s", it.codeProduct, it.idOrder))
            mysqlOrderRepository.update(it.codeProduct, it.idOrder)
        }
        println("finished!")
    }
}