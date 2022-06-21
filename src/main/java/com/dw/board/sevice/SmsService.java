package com.dw.board.sevice;

import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleSend
 * @brief This sample code demonstrate how to send sms through CoolSMS Rest API PHP
 */
public class SmsService {
  public static void main(String[] args) {
    String api_key = "NCS7DBM8WA7VX6HQ";
    String api_secret = "AVVESDWKJQMYQGCPLKHKUCJHFXYNND8H";
    Message coolsms = new Message(api_key, api_secret);

    // 4 params(to, from, type, text) are mandatory. must be filled
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("to", "01095071415");
    params.put("from", "01053064635"); //무조건 자기번호 (인증)
    params.put("type", "SMS");
    params.put("text", "아아 응답바람 오바");
    params.put("app_version", "test app 1.2"); // application name and version

    try {
    	//send() 는 메시지를 보내는 함수  
      JSONObject obj = (JSONObject) coolsms.send(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}