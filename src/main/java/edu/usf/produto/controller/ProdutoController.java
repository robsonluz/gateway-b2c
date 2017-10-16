package edu.usf.produto.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired RestTemplate restTemplate;
	
	@RequestMapping(path="", method=RequestMethod.GET)
	public HttpEntity<?> find() {
		return restTemplate.getForEntity("http://service-produto:8080/produtos", Object.class);
	}
	
	@RequestMapping(path="", method=RequestMethod.POST)
	public HttpEntity<?> save(@RequestBody Map<String, Object> produto) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Map<String, Object>> request = new HttpEntity<>(produto, headers);
		return restTemplate.postForEntity("http://service-produto:8080/produtos", request, Object.class);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public HttpEntity<?> delete(@PathVariable Long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Map<String, Object>> request = new HttpEntity<>(headers);
		restTemplate.delete("http://service-produto:8080/produtos/" + id, request);
		return new ResponseEntity<>(HttpStatus.OK);
	}	
}
