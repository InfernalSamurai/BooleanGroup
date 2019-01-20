package BooleanGroup

import BooleanGroupData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BooleanGroupController {

    @GetMapping("/BooleanGroup")
    fun booleanGroup(@RequestParam(value = "weight", defaultValue = "1") weight: Int,
                     @RequestParam(value = "height", defaultValue = "0") height: Int) = BooleanGroupData(weight, height)

}

