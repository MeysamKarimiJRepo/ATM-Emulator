package com.meysam.emulator.atm.service;

import com.meysam.emulator.atm.dto.AuthenticationResponse;
import com.meysam.emulator.atm.dto.CheckingCardResult;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class MyAuthenticationManager implements AuthenticationProvider {

    @Value("${bank.service.url}")
    private String bankServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String pan = authentication.getName();
        String secret = authentication.getCredentials().toString();
///checkCardNumber/{pan}
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(bankServiceUrl + "/checkCardNumber/")
                .queryParam("pan", "{pan}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("pan", pan);


        HttpEntity<CheckingCardResult> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                CheckingCardResult.class,
                params);

        if (response.getBody() == null || response.getBody().getOwnerName() == null || response.getBody().getPreferredAuthentication() == null) {
            throw new AuthenticationException(pan) {
                @Override
                public String getMessage() {
                    return "Invalid pan " + pan;
                }
            };
        }
        CheckingCardResult c = response.getBody();
        if (c.getPreferredAuthentication().equals("FingerPring")) {

            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject myJsonObject = new JSONObject();
            myJsonObject.put("pan", pan);
            myJsonObject.put("fingerPrint", secret);
            AuthenticationResponse result = restTemplate.postForObject(bankServiceUrl + "/authenticateWithPin", myJsonObject, AuthenticationResponse.class);
            if (result.isAuthenticated()){
                authentication.setAuthenticated(true);
                return authentication;
            }
        } else if (c.getPreferredAuthentication().equals("Pin")) {

            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject myJsonObject = new JSONObject();
            myJsonObject.put("pan", pan);
            myJsonObject.put("fingerPrint", secret);
            AuthenticationResponse result = restTemplate.postForObject(bankServiceUrl + "/authenticateWithFingerPrint", myJsonObject, AuthenticationResponse.class);
            if (result.isAuthenticated()){
                authentication.setAuthenticated(true);
                return authentication;
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
