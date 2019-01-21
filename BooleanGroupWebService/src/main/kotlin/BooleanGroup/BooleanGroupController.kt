package BooleanGroup

import BooleanGroupData
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class BooleanGroupController {

    @GetMapping("/BooleanGroup")
    fun booleanGroup(@RequestParam(value = "weight", defaultValue = "1") weight: Int,
                     @RequestParam(value = "height", defaultValue = "0") height: Int): BooleanGroupData {
        val booleanGroupData = BooleanGroupData(weight, height)
        produceInKafka(booleanGroupData)
        return booleanGroupData
    }

    private fun produceInKafka(booleanGroupData: BooleanGroupData) {
        val kafkaProducer = createKafkaProducer("localhost:9092")
        val jsonMapper = ObjectMapper().apply { registerKotlinModule() }
        val mappedBooleanGroupData = jsonMapper.writeValueAsString(booleanGroupData)
        kafkaProducer.send(ProducerRecord("BooleanGroupData", mappedBooleanGroupData))
    }

    private fun createKafkaProducer(brokers: String): KafkaProducer<String, String> {
        val properties = Properties()
        properties["bootstrap.servers"] = brokers
        properties["key.serializer"] = StringSerializer::class.java
        properties["value.serializer"] = StringSerializer::class.java
        return KafkaProducer(properties)
    }

}

