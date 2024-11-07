package br.com.ciceroednilson.repository.query

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory

class QueryMySQL(val findAllOrders: String, val updateOrderXProduct: String) {

    companion object {
        fun builder(): QueryMySQL {
            val text = javaClass.getResource("/queries/mysql-queries.yml")?.readText()
            val objectMapper = ObjectMapper(YAMLFactory())
            objectMapper.findAndRegisterModules()
            return objectMapper.readValue(text, QueryMySQL::class.java)
        }
    }
}