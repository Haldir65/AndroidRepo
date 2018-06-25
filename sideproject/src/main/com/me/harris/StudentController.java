package com.me.harris;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {


    @RequestMapping("/hello")
    public String sayHello(@RequestParam(value = "name")String params){
        return "Hello"+params+"!";
    }

}
