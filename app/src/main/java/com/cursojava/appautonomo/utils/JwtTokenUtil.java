package com.cursojava.appautonomo.utils;

import android.content.SharedPreferences;
import android.util.Base64;

import com.cursojava.appautonomo.model.JwtToken;
import com.cursojava.appautonomo.model.TokenBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtTokenUtil {

    public static TokenBody decode(JwtToken jwtToken) {

        String jwt = jwtToken.getValue();

        int bodyStartIndex = jwt.indexOf(".") + 1;
        int bodyEndIndex = jwt.indexOf(".", bodyStartIndex);
        String bodyBase64 = jwt.substring(bodyStartIndex, bodyEndIndex);
        byte[] bodyBytes = android.util.Base64.decode(bodyBase64, Base64.DEFAULT);
        String bodyJson = new String(bodyBytes);
        System.out.println(bodyJson);
        TokenBody tokenBody = null;
        try {
            tokenBody = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(bodyJson, TokenBody.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return tokenBody;
    }

}
