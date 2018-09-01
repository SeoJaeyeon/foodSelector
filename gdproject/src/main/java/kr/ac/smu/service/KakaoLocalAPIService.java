package kr.ac.smu.service;

import org.springframework.http.ResponseEntity;

public interface KakaoLocalAPIService {
	//완전랜덤
	public ResponseEntity complete(String x, String y);
	
	//키워드랜덤
	public ResponseEntity keyword(String x, String y, String keyword);
}
