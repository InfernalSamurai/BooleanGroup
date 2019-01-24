package ru.hei.web.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.hei.web.service.entity.BooleanGroupData
import java.util.*

@RestController
class BooleanGroupController {
    @Autowired
    lateinit var message: BooleanGroupKafkaMessage

    @GetMapping("/booleanGroup")
    fun booleanGroup(@RequestParam(value = "width", defaultValue = "1") width: Int,
                     @RequestParam(value = "height", defaultValue = "0") height: Int): String {
        val booleanGroupData = BooleanGroupData(UUID.randomUUID().mostSignificantBits, width, height)
        message.send(booleanGroupData)
        return "It will create file booleanGroup${booleanGroupData.id}.txt"
    }
}

