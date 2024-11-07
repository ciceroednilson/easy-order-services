package br.com.ciceroednilson.repository.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource


@Configuration
class DataSourceConfig {

    @Bean(name = ["mysql"])
    fun dataSourceMysql(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver")
        dataSource.url = "jdbc:mysql://127.0.01:3306/db_system"
        dataSource.username = "root"
        dataSource.password = "123456"
        return dataSource
    }

    @Bean(name = ["mariadb"])
    fun dataSourceMariadb(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.url = "jdbc:mariadb://127.0.0.1:3000/db_system_bi"
        dataSource.username = "root"
        dataSource.password = "123456"
        return dataSource
    }

    @Bean(name = ["jdbcTemplateMysql"])
    fun jdbcTemplateMysql(@Qualifier("mysql") dataSource1: DataSource?): JdbcTemplate {
        return JdbcTemplate(dataSource1!!)
    }

    @Bean(name = ["jdbcTemplateMariadb"])
    fun jdbcTemplateMariadb(@Qualifier("mariadb") dataSource2: DataSource?): JdbcTemplate {
        return JdbcTemplate(dataSource2!!)
    }
}