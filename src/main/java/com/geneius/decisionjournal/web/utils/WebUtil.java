package com.geneius.decisionjournal.web.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.geneius.decisionjournal.web.security.SecurityConstants.EXPIRATION_TIME;
import static com.geneius.decisionjournal.web.security.SecurityConstants.SECRET;

public class WebUtil {
  public static Map<String, Object> getData(Object result) {
    Map<String, Object> dataMap = new HashMap(1);
    dataMap.put("data", result);
    return dataMap;
  }

  public static String generateJWT(String email) {
    return Jwts.builder()
        .setSubject(email)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
        .compact();
  }
}
