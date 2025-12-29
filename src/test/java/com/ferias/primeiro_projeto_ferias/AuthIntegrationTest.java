package com.ferias.primeiro_projeto_ferias;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void registerAndLoginShouldReturnToken() {
        String username = "testuser" + System.currentTimeMillis();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = Map.of("username", username, "password", "ignored");
        HttpEntity<Map<String,String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> regRes = restTemplate.postForEntity("/auth/register", request, Map.class);
        assertEquals(HttpStatus.OK, regRes.getStatusCode());

        Map<String,String> loginBody = Map.of("username", username, "password", "password");
        ResponseEntity<Map> loginRes = restTemplate.postForEntity("/auth/login", new HttpEntity<>(loginBody, headers), Map.class);
        assertEquals(HttpStatus.OK, loginRes.getStatusCode());
        assertNotNull(loginRes.getBody());
        assertTrue(loginRes.getBody().containsKey("token"));
    }
}
