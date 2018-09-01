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
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.smu.DTO.CustomDTO;
import kr.ac.smu.DTO.CustomPlaceDTO;
import kr.ac.smu.DTO.PlaceDTO;
import kr.ac.smu.service.CustomInfoService;
import kr.ac.smu.service.KakaoLocalAPIService;


@Controller
@PropertySource("classpath:settingKey.properties")
public class FoodSelectorController {
	
	Logger logger = LoggerFactory.getLogger(FoodSelectorController.class);
	
	@Autowired 
	private KakaoLocalAPIService kakaoLocalAPIService;
	
	@Autowired
	private CustomInfoService customInfoService;
	
	// 완전 랜덤
	@RequestMapping(value="/completerandom", method=RequestMethod.POST,produces="application/json;charset=UTF-8" )
	@ResponseBody
	public Map<Integer,CustomPlaceDTO> completeRandom(HttpServletRequest req) throws ParseException, JsonParseException, JsonMappingException, IOException{
	
		ResponseEntity response= kakaoLocalAPIService.complete(req.getParameter("x"), req.getParameter("y"));
		
		Map<Integer,CustomPlaceDTO> place_list;
		try{
			place_list=parse(response);
		}catch(Exception e){
			return null;
		}
		
		return place_list;
	}
	
	public Map<Integer, CustomPlaceDTO> parse(ResponseEntity res) throws ParseException, JsonParseException, JsonMappingException, IOException{

		JSONParser jsonParser = new JSONParser(); 
		JSONObject jsonObject = (JSONObject) jsonParser.parse(res.getBody().toString()); 

		JSONArray docuArray = (JSONArray) jsonObject.get("documents");
 
		ObjectMapper mapper=new ObjectMapper();
		CustomPlaceDTO place;
		Map<Integer, CustomPlaceDTO> place_list=new HashMap<Integer,CustomPlaceDTO>();
		for(int i=0; i<15; i++){
			JSONObject obj=(JSONObject) docuArray.get(i);
			place = (CustomPlaceDTO)mapper.readValue(obj.toString(), CustomPlaceDTO.class);
			
			place_list.put(i+1,place);
		}
		
		return place_list;
	}


	//커스텀조회
	/*
	 * parameter: userId, customName
	 */
	@RequestMapping(value="/customrandom", method=RequestMethod.GET)
	@ResponseBody
	public Map<Integer,CustomPlaceDTO> customRandom(HttpServletRequest req) throws ParseException{
		String userId=req.getParameter("userId");
		String customName=req.getParameter("customName");
		
		Map<Integer, CustomPlaceDTO> customs=customInfoService.selectAllCustomsByCustomName(userId, customName);
		
		return customs;
	}
	
	//커스텀추가 
	//param: placeDTO, CustomName, userId 
	@RequestMapping(value="/addcustom", method=RequestMethod.POST)
	@ResponseBody
	public boolean addCustom(PlaceDTO place, HttpServletRequest req) throws ParseException{
			String user_id=req.getParameter("userId");
			String customName=req.getParameter("customName");
			try{
				customInfoService.addCustom(place, user_id, customName);
			}catch(Exception e)
			{
				logger.error(e.getMessage());
				return false;
			}
			return true;
		}


}
