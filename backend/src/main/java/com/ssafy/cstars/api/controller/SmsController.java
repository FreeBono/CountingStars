package com.ssafy.cstars.api.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.cstars.api.request.Request;
import com.ssafy.cstars.api.response.SmsResponse;
import com.ssafy.cstars.service.SendSMSTwilio;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SmsController {

    private final SendSMSTwilio twilio;

    @PostMapping("/api/v1/auth/signup/phone")
    public ResponseEntity<Integer> phonetest(@RequestBody Request request){

        int code = twilio.sendSMS(request.getCountry(), request.getPhoneNum());
        System.out.println(code);

        return new ResponseEntity<Integer>(code, HttpStatus.CREATED);
    } 
}
