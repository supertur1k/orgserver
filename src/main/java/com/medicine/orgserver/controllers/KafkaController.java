package com.medicine.orgserver.controllers;

import com.medicine.orgserver.kafka.KafkaProducer;
import com.medicine.orgserver.repositories.CatRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    public KafkaController(CatRepo catRepo, KafkaProducer kafkaProducer) {
        this.catRepo = catRepo;
        this.kafkaProducer = kafkaProducer;
    }

    private final CatRepo catRepo;
    private final KafkaProducer kafkaProducer;

    @PostMapping("/kafka/send")
    public String send(@RequestParam int id) {
        var cat = catRepo.findById(id);
        kafkaProducer.sendMessage(cat.toString());
        return "Success";
    }
}
