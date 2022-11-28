package com.example.Nurse.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping

public class NurseController {

    @KafkaListener(topics = "patientvisit",groupId = "group_id")
    public void HospitalvisitConsumer(String message) {
        System.out.println("message = " + message);
    }
    @KafkaListener(topics = "ObsevationCompleted",groupId = "group_id")
    public void ObservationCompletedConsumer(String message) {
        System.out.println("message = " + message);
    }
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
    private static final String TOPIC3 = "ObservationStart";
    @GetMapping("/ObservationStart")
    public String ObservationProducer(@RequestBody String message){
        kafkaTemplate.send(TOPIC3,message);
        return "success";
    }

    private static final String TOPIC6 = "PhysicalExam";
    @GetMapping("/PhysicalExam")
    public String PhysicalEXamProducer(@RequestBody String message){
        kafkaTemplate.send(TOPIC3,message);
        return "success";}

}
