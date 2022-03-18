package com.ssafy.cstars.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.stereotype.Service;

@Service
public class SendSMSTwilio {
	
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC42e279fa94ea92db4fac4130c3138ca1";
    public static final String AUTH_TOKEN = "ff337073878cef364781bb826dc4036b";
    
    // SMS 전송
    public static int sendSMS (String country, String phoneNum) {
  
      Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      
      // 휴대폰 인증번호 생성
      int authNum = randomRange(100000, 999999);
      
      
      // 전송대상 휴대폰 번호
      String sendTarget = "+"+ country + phoneNum;
      
      // 전송 메세지
      String authMsg = "The authentication number is [" + authNum + "]" ;
      
      
      Message message = Message.creator(
          // to
          new PhoneNumber(sendTarget),
          // from
          new PhoneNumber("+17657700987"), 
          // message
          authMsg).create();
      
      return authNum;
      
    }
      
    // 인증번호 범위 지정
    public static int randomRange(int n1, int n2) {
      return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }
    
    
  }
