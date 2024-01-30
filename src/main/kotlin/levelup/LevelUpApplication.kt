package levelup

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class LevelUpApplication

fun main(args: Array<String>) {
    runApplication<LevelUpApplication>(*args)
}
