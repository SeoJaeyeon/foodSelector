package kr.ac.smu;
import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.smu.DTO.PlaceDTO;

@Controller
public class KaKaoAPIServerController {
	@Value("${setting.appKey}")
	private String appKey;
	
	@RequestMapping(value="/crandom", method=RequestMethod.GET,produces = "application/json; charset=utf8")
	@ResponseBody
	public PlaceDTO completeRandom(HttpServletRequest req) throws ParseException, JsonParseException, JsonMappingException, IOException{
		RestTemplate restTemplate = new RestTemplate(); 
		HttpHeaders headers = new HttpHeaders(); 
		headers.add("Content-Type", "application/json;charset=UTF-8");

		headers.set("Authorization", appKey); //appKey 설정 ,KakaoAK kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk 이 형식 준수

		HttpEntity entity = new HttpEntity("parameters", headers); 
		URI url=URI.create("https://dapi.kakao.com/v2/local/search/keyword.json?query=%EA%B9%80%EC%B9%98%EC%B0%8C%EA%B0%9C&category_group_code=FD6&x="+req.getParameter("x")+"&y="+req.getParameter("y")); 
		ResponseEntity response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		//String 타입으로 받아오면 JSON 객체 형식으로 넘어옴
		
		JSONParser jsonParser = new JSONParser(); 
		JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString()); 
		
		JSONArray docuArray = (JSONArray) jsonObject.get("documents");
		
		ObjectMapper mapper=new ObjectMapper();
		PlaceDTO bean = mapper.readValue(docuArray.get(0).toString(), PlaceDTO.class);

		 
		//JSONObject obj=(JSONObject) docuArray.get(0);

		return bean;
	}
}
