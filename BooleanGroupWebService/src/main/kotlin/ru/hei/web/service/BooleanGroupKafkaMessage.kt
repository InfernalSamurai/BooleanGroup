package ru.hei.web.service

import BooleanGroupData
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class BooleanGroupKafkaMessage {
    var topicName: String = ""
    private val mapper = jacksonObjectMapper()

    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    fun withTopic(topicName: String): BooleanGroupKafkaMessage {
        this.topicName = topicName
        return this
    }

    fun send(data: BooleanGroupData) {
        kafkaTemplate.send(topicName, mapper.writeValueAsString(data))
    }
}
