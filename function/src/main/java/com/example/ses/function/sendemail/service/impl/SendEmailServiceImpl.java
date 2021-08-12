package com.example.ses.function.sendemail.service.impl;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.example.ses.function.sendemail.model.SendEmailRequest;
import com.example.ses.function.sendemail.service.SendEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SendEmailServiceImpl implements SendEmailService {

    @Override
    public void sendEmail(String body) {
        log.info("Service: " + body);

        try {
            Gson gson = new GsonBuilder().create();
            SendEmailRequest sendEmailRequest = gson.fromJson(body, SendEmailRequest.class);

            String emailsender = System.getenv("EMAILSENDER");
            String emailrecipent = System.getenv("EMAILRECIPENT");
            String template = sendEmailRequest.getTemplate();
            String templateData = new Gson().toJson(sendEmailRequest.getTemplateData());

            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            .withRegion(Regions.US_EAST_2).build();

            Destination destination = new Destination();
            List<String> toAddresses = new ArrayList<String>();
            toAddresses.add(emailrecipent);
            destination.setToAddresses(toAddresses);

            SendTemplatedEmailRequest templatedEmailRequest = new SendTemplatedEmailRequest()
                    .withDestination(destination)
                    .withTemplate(template)
                    .withTemplateData(templateData)
                    .withSource(emailsender);

            client.sendTemplatedEmail(templatedEmailRequest);
            log.info("Email sent!");
        } catch (Exception ex) {
            log.info("The email was not sent. Error message: "
                    + ex.getMessage());
        }
    }
}
