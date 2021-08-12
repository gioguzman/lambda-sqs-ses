package com.example.ses.function.sendemail.controller;

import com.example.ses.function.sendemail.service.SendEmailService;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
@Slf4j
public class SendEmailFunction implements Function<SQSEvent, Void> {

    @Autowired
    private SendEmailService sendEmailService;

    @Override
    public Void apply(SQSEvent sqsEvent) {
        for (SQSEvent.SQSMessage msg : sqsEvent.getRecords()){
            log.info("Controller: " + msg.getBody());
            sendEmailService.sendEmail(msg.getBody());
        }
        return null;
    }
}
