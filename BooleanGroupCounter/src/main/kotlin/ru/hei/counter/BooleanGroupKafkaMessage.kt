package ru.hei.counter

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
    @Value("\${matrix.group.directory.to.write}")
    private lateinit var directory: String
    private val mapper = jacksonObjectMapper()

    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    fun send(data: BooleanGroupData) = kafkaTemplate.send(topicName, mapper.writeValueAsString(data))

    @KafkaListener(topics = ["\${matrix.group.consumer.topic}"])
    @Throws(Exception::class)
    fun receive(@Payload message: String, @Headers messageHeaders: MessageHeaders) {
        mapper.readValue(message, BooleanGroupData::class.java)?.let {
            val data = BooleanGroupCounter(it).countGroupsAndGetData()
            BooleanGroupFile(data).writeToDirectory(directory)
        }
    }

}

