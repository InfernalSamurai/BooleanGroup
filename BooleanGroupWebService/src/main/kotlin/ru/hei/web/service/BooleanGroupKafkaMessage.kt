package ru.hei.web.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.hei.web.service.entity.BooleanGroupData

@Service
class BooleanGroupKafkaMessage {
    @Value("\${matrix.group.producer.topic}")
    private lateinit var topicName: String
    private val mapper = jacksonObjectMapper()

    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    fun send(data: BooleanGroupData) = kafkaTemplate.send(topicName, mapper.writeValueAsString(data))
}
