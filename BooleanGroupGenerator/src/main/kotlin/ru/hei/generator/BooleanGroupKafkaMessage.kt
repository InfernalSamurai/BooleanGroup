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
    private lateinit var topicName: String
    private val mapper = jacksonObjectMapper()

    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    fun send(data: BooleanGroupData) = kafkaTemplate.send(topicName, mapper.writeValueAsString(data))

    @KafkaListener(topics = ["\${matrix.group.consumer.topic}"])
    @Throws(Exception::class)
    fun receive(@Payload message: String, @Headers messageHeaders: MessageHeaders) {
        mapper.readValue(message, BooleanGroupData::class.java)?.let {
            send(BooleanGroupGenerator(it).generateMatrixAndGetData())
        }
    }
}
