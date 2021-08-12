package com.example.ses.function.sendemail.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class SendEmailRequest {

    private String to;
    private String template;
    private Map<String,String> templateData;
}
