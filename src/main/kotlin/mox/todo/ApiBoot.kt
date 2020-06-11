package mox.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiBoot

fun main(args: Array<String>) {
    runApplication<ApiBoot>(*args)
}
