package icu.epq.push.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/push")
public class HelloController {

    @GetMapping("/kafka/msg")
    public String hello() {
        return "hello";
    }

}
