package com.medicine.orgserver.controllers;

import com.medicine.orgserver.kafka.KafkaProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    private final KafkaProducer kafkaProducer;

    @PostMapping("/kafka/send")
    public String send(@RequestParam int id) {
        kafkaProducer.sendMessage("");
        return "Success";
    }
}
