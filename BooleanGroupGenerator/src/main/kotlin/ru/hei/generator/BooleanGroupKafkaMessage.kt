package ru.hei.generator

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class BooleanGroupKafkaMessage {
    @Value("\${matrix.group.producer.topic}")
    var topicName: String = ""
    private val mapper = jacksonObjectMapper()

    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    fun withTopic(topicName: String): BooleanGroupKafkaMessage {
        this.topicName = topicName
        return this
    }

    fun send(data: String) {
        kafkaTemplate.send(topicName, data)
    }

    @KafkaListener(topics = ["\${matrix.consumer.topic}"])
    fun receive(@Payload message: String, @Headers messageHeaders: MessageHeaders) {
        val (weight, height) = mapper.readValue(message, BooleanGroupData::class.java)
        val matrix = BooleanGroupGenerator().generate(height, weight)
        val serialisedMatrix = mapper.writeValueAsString(matrix)
        send(serialisedMatrix)
    }
}
