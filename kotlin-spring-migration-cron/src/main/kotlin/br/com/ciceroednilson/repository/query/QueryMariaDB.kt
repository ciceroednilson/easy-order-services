package br.com.ciceroednilson.repository.query

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory

class QueryMariaDB(val insertOrder: String) {

    companion object {
        fun builder(): QueryMariaDB {
            val text = javaClass.getResource("/queries/mariadb-queries.yml")?.readText()
            val objectMapper = ObjectMapper(YAMLFactory())
            objectMapper.findAndRegisterModules()
            return objectMapper.readValue(text, QueryMariaDB::class.java)
        }
    }
}