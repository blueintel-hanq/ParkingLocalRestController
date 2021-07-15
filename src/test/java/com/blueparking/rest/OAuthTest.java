package com.blueparking.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParkingLocalRestControllerApplication.class)
public class OAuthTest {

    private String clientId     = "c2f87df4460b48bfa19b9d6a7794d217";
    private String clientSecret = "2AD3537785B3995A5B86DB915B9E1399F447E188CBC29CBB20A5AA754B51FF38";

    @Test
    public void getJwtTokenByClientCredentialForUser() throws JsonParseException, JsonMappingException, IOException {
        ResponseEntity<String> response =
                new TestRestTemplate(
                        clientId, clientSecret)
                        .postForEntity("http://localhost:" + 9090 + "/oauth/token?grant_type=password&username=super&password=elwlxjffh31", null, String.class);
        String responseText = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        HashMap jwtMap = new ObjectMapper().readValue(responseText, HashMap.class);

        assertEquals("bearer", jwtMap.get("token_type"));
        assertEquals("read write profile", jwtMap.get("scope"));
        assertTrue(jwtMap.containsKey("access_token"));
        assertTrue(jwtMap.containsKey("expires_in"));
        assertTrue(jwtMap.containsKey("jti"));
        String accessToken = (String) jwtMap.get("access_token");

        System.out.println("accessToken : " + accessToken);

        Jwt jwtToken = JwtHelper.decode(accessToken);
        String claims = jwtToken.getClaims();
        HashMap claimsMap = new ObjectMapper().readValue(claims, HashMap.class);
        //assertEquals("spring-boot-application", ((List<String>) claimsMap.get("aud")).get(0));
        //assertEquals("trusted-app", claimsMap.get("client_id"));
        assertEquals("super", claimsMap.get("user_name"));
        //assertEquals("read", ((List<String>) claimsMap.get("scope")).get(0));
        //assertEquals("write", ((List<String>) claimsMap.get("scope")).get(1));
        assertEquals("ROLE_SUPER", ((List<String>) claimsMap.get("authorities")).get(0));

    }



}
