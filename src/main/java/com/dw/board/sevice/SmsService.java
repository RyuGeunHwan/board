package com.dw.board.sevice;

import java.util.HashMap;

import com.mysql.cj.protocol.Message;



/**
 * @class ExampleSend
 * @brief This sample code demonstrate how to send sms through CoolSMS Rest API PHP
 */
public class SmsService {
  public static void main(String[] args) {
    String api_key = "NCS7DBM8WA7VX6HQ";
    String api_secret = "FKZAS4AK1KLAUI9U7SS8DECHXC5VIDVZ";
    Message coolsms = new Message(api_key, api_secret);

    // 4 params(to, from, type, text) are mandatory. must be filled
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("to", "01053064635");
    params.put("from", "01053064635");
    params.put("type", "SMS");
    params.put("text", "Coolsms Testing Message!");
    params.put("app_version", "test app 1.2"); // application name and version

    try {
      JSONObject obj = (JSONObject) coolsms.send(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
}