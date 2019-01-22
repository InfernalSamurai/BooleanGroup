package ru.hei.web.service

import BooleanGroupData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BooleanGroupController {
    @Autowired
    lateinit var message: BooleanGroupKafkaMessage

    @GetMapping("/ru/hei/web/service")
    fun booleanGroup(@RequestParam(value = "weight", defaultValue = "1") weight: Int,
                     @RequestParam(value = "height", defaultValue = "0") height: Int): BooleanGroupData {
        val booleanGroupData = BooleanGroupData(weight, height)
        message.withTopic("BooleanGroupData").send(booleanGroupData)
        return booleanGroupData
    }
}

