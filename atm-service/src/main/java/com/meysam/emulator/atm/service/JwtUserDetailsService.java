package com.meysam.emulator.atm.service;

import com.meysam.emulator.atm.dto.CheckingCardResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("bank.service.url")
	public String bankServiceUrl;
	@Override
	public UserDetails loadUserByUsername(String pan) throws UsernameNotFoundException {

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
			throw new UsernameNotFoundException("Card not found with pan: " + pan);
		}else{
			return new User(pan, null,
					new ArrayList<>());
		}
	}
}