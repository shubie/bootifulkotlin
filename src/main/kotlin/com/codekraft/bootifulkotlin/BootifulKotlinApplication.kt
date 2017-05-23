package com.codekraft.bootifulkotlin

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.annotation.Id
import java.util.stream.Stream

@SpringBootApplication
open class BootifulKotlinApplication (val personRepository : PersonRepository) : CommandLineRunner {
    override fun run(vararg p0: String?) {
//        throw UnsupportedOperationException()

        personRepository.deleteAll()

        Stream.of("Shuaib, Afegbua", "Ozo,Esone", "Haliru, Hakim")
              .map { fn -> fn.split(",") }
              .forEach {personTurple -> personRepository.save(Person(personTurple[0],personTurple[1]))}

        personRepository.all().forEach { println(it) }
    }


}

fun main(args: Array<String>) {
    SpringApplication.run(BootifulKotlinApplication::class.java, *args)
}

interface PersonRepository : MongoRepository<Person, String>{
    @Query("{}")
    fun all(): Stream<Person>
}

@Document
data class Person (var first: String? = null, var last: String? = null, @Id var id: String? = null)