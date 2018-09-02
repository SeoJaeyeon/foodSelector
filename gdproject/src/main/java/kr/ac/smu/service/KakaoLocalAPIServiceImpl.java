package kr.ac.smu.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoLocalAPIServiceImpl implements KakaoLocalAPIService {
	@Value("${setting.appKey}")
	private String appKey;
	
	
	@Override
	public ResponseEntity<String> complete(String x, String y) {
		RestTemplate restTemplate = new RestTemplate(); 

		HttpHeaders headers = new HttpHeaders(); 

		headers.add("Content-Type", "application/json;charset=UTF-8");
		headers.set("Authorization", appKey);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers); 
		URI url=URI.create("https://dapi.kakao.com/v2/local/search/keyword.json?query=%EA%B9%80%EC%B9%98%EC%B0%8C%EA%B0%9C&size=15&category_group_code=FD6&x="+x+"&y="+y); 

		ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		
		return response;
	}

	@Override
	public ResponseEntity<String> keyword(String x, String y, String keyword) {
		
		RestTemplate restTemplate = new RestTemplate(); 

		HttpHeaders headers = new HttpHeaders(); 

		headers.add("Content-Type", "application/json;charset=UTF-8");
		headers.set("Authorization", appKey);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers); 
		URI url=URI.create("https://dapi.kakao.com/v2/local/search/keyword.json?query="+keyword+"&category_group_code=FD6&x="+x+"&y="+y+"&sort=accuracy&size=15"); 

		ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		
		return response;
	}

}
