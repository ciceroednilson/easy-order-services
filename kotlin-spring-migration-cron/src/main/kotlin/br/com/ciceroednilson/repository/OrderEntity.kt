package br.com.ciceroednilson.repository

import java.math.BigDecimal
import java.time.LocalDateTime

data class OrderEntity(
    val idOrder: Long,
    val codeProduct: Long,
    val descProduct: String,
    val valueProduct: BigDecimal,
    val codeCategory: Long,
    val descCategory: String,
    val dateCreated: LocalDateTime,
    val flagMigrated: Boolean,
    var dateMigrated: LocalDateTime,
)
