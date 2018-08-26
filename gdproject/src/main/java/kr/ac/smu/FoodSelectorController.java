package kr.ac.smu;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
import kr.ac.smu.service.CustomInfoService;


@Controller
@PropertySource("classpath:settingKey.properties")
public class FoodSelectorController {
	Logger logger = LoggerFactory.getLogger(FoodSelectorController.class);
	@Value("${setting.appKey}")
	private String appKey;
	
	@Autowired
	private CustomInfoService customInfoService;
	
	// 완전 랜덤
	@RequestMapping(value="/completerandom", method=RequestMethod.POST,produces="application/json;charset=UTF-8" )
	@ResponseBody
	public Map<Integer,PlaceDTO> completeRandom(HttpServletRequest req) throws ParseException, JsonParseException, JsonMappingException, IOException{
		RestTemplate restTemplate = new RestTemplate(); 

		HttpHeaders headers = new HttpHeaders(); 

		headers.add("Content-Type", "application/json;charset=UTF-8");
		headers.set("Authorization", appKey); //appKey 설정 ,KakaoAK kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk 이 형식 준수

		HttpEntity entity = new HttpEntity("parameters", headers); 
		URI url=URI.create("https://dapi.kakao.com/v2/local/search/keyword.json?query=%EA%B9%80%EC%B9%98%EC%B0%8C%EA%B0%9C&size=15&category_group_code=FD6&x="+req.getParameter("x")+"&y="+req.getParameter("y")); 
		//x -> x좌표, y -> y좌표 

		ResponseEntity response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		//String 타입으로 받아오면 JSON 객체 형식으로 넘어옴

		JSONParser jsonParser = new JSONParser(); 
		JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString()); 

		//저는 Body 부분만 사용할거라 getBody 후 JSON 타입을 인자로 넘겨주었습니다

		//헤더도 필요하면 getBody()는 사용할 필요가 없겠쥬
		JSONArray docuArray = (JSONArray) jsonObject.get("documents");
		//documents만 뽑아오고  

		ObjectMapper mapper=new ObjectMapper();
		PlaceDTO place;
		Map<Integer, PlaceDTO> place_list=new HashMap<Integer,PlaceDTO>();
		for(int i=0; i<15; i++){
			JSONObject obj=(JSONObject) docuArray.get(i);
			place = mapper.readValue(obj.toString(), PlaceDTO.class);
			
			place_list.put(i+1,place);
		}
		
		logger.info(new Date()+"/completerandom POST Request");
		return place_list;
	}

	//커스텀추가 
	@RequestMapping(value="/customrandom", method=RequestMethod.POST)
	@ResponseBody
	public Map<Integer,PlaceDTO> customRandom(HttpServletRequest req) throws ParseException{
		String userId=req.getParameter("userId");
		String customName=req.getParameter("customName");
		List<String> placeidList=customInfoService.selectAllCustomId(userId, customName);
		logger.info(userId+", "+customName);
		List<PlaceDTO> placeList=new ArrayList<PlaceDTO>();
		for(int i=0; i<placeidList.size(); i++){
			placeList.add(customInfoService.selectPlaceByCustomId(placeidList.get(i)));
		}
		Map<Integer,PlaceDTO> customData=new HashMap<Integer,PlaceDTO>();
		for(int i=0; i<placeList.size(); i++)
			customData.put(i, placeList.get(i));
		return customData;
	}



}
