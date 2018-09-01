package kr.ac.smu.service;

import java.net.URI;

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
	public ResponseEntity complete(String x, String y) {
		RestTemplate restTemplate = new RestTemplate(); 

		HttpHeaders headers = new HttpHeaders(); 

		headers.add("Content-Type", "application/json;charset=UTF-8");
		headers.set("Authorization", appKey);

		HttpEntity entity = new HttpEntity("parameters", headers); 
		URI url=URI.create("https://dapi.kakao.com/v2/local/search/keyword.json?query=%EA%B9%80%EC%B9%98%EC%B0%8C%EA%B0%9C&size=15&category_group_code=FD6&x="+x+"&y="+y); 

		ResponseEntity response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		
		return response;
	}

	@Override
	public ResponseEntity keyword(String x, String y, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
