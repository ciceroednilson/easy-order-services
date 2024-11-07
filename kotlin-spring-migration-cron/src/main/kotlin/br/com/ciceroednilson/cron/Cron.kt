package br.com.ciceroednilson.cron

import br.com.ciceroednilson.service.MigrationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Cron(
    @Autowired var migrationService: MigrationService
) {
    //execute each 2 seconds
    @Scheduled(cron = "*/2 * * * * ?")
    fun start() {
        println("starting the job...")
        migrationService.execute()
        println("finished the job...")
    }
}