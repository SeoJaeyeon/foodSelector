package kr.ac.smu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import kr.ac.smu.DTO.CustomPlaceDTO;
import kr.ac.smu.DTO.PlaceDTO;
import kr.ac.smu.service.CustomInfoService;
import kr.ac.smu.service.KakaoLocalAPIService;


@RestController
@PropertySource("classpath:settingKey.properties")
public class FoodSelectorController {
	
	Logger logger = LoggerFactory.getLogger(FoodSelectorController.class);
	
	@Autowired 
	private KakaoLocalAPIService kakaoLocalAPIService;
	
	@Autowired
	private CustomInfoService customInfoService;

	// 완전 랜덤
	/*
	 * parameter= x, y
	 */
	@RequestMapping(value="/completerandom", method={RequestMethod.POST, RequestMethod.GET},produces="application/json;charset=UTF-8" )
	public Map<Integer,CustomPlaceDTO> completeRandom(HttpServletRequest req) throws ParseException, JsonParseException, JsonMappingException, IOException{
	
		ResponseEntity<String> response= kakaoLocalAPIService.complete(req.getParameter("x"), req.getParameter("y"));
		Map<Integer,CustomPlaceDTO> place_list;
		try{
			place_list=parse(response);
		}catch(Exception e){
			return null;
		}
		
		return place_list;
	}
	
	//키워드 랜덤
	/*
	 * parameter = x, y, keyword
	 */
	@RequestMapping(value="/keywordrandom", method={RequestMethod.POST, RequestMethod.GET},produces="application/json;charset=UTF-8")
	public Map<Integer, CustomPlaceDTO> keywordRandom(HttpServletRequest req){

		ResponseEntity<String> response= kakaoLocalAPIService.keyword(req.getParameter("x"), req.getParameter("y"),req.getParameter("keyword"));
		Map<Integer,CustomPlaceDTO> place_list;
		try{
			place_list=parse(response);
		}catch(Exception e){
			return null;
		}
		
		return place_list;
	}
	
	public Map<Integer, CustomPlaceDTO> parse(ResponseEntity<String> res) throws ParseException, JsonParseException, JsonMappingException, IOException{
		System.out.println(res);
		JSONParser jsonParser = new JSONParser(); 
		JSONObject jsonObject = (JSONObject) jsonParser.parse(res.getBody().toString()); 

		JSONArray docuArray = (JSONArray) jsonObject.get("documents");
 
		ObjectMapper mapper=new ObjectMapper();
		CustomPlaceDTO place;
		Map<Integer, CustomPlaceDTO> place_list=new HashMap<Integer,CustomPlaceDTO>();
		for(int i=0; i<15; i++){
			JSONObject obj=(JSONObject) docuArray.get(i);
			place = (CustomPlaceDTO)mapper.readValue(obj.toString(), PlaceDTO.class);
			
			place_list.put(i+1,place);
		}

		return place_list;
	}
	
	//커스텀목록 조회
	/*
	 * parameter: user_id
	 */
	@RequestMapping(value="/customlist", method={RequestMethod.POST, RequestMethod.GET})
	public Map<Integer, String> customList(String user_id){
		return customInfoService.selectCustomListById(user_id);
	}
	
	//커스텀조회
	/*
	 * parameter: userId, customName
	 */
	@RequestMapping(value="/customrandom", method={RequestMethod.POST, RequestMethod.GET})
	public Map<Integer,CustomPlaceDTO> customRandom(String user_id, String custom_name) throws ParseException{
		String userId=user_id;
		String customName=custom_name;
		
		Map<Integer, CustomPlaceDTO> customs=customInfoService.selectAllCustomsByCustomName(userId, customName);
		
		return customs;
	}
	
	//커스텀추가 
	/*
	 * parameter: place, user_id, custom_name
	 */
	@RequestMapping(value="/addcustom", method={RequestMethod.POST, RequestMethod.GET})
	public boolean addCustom(CustomPlaceDTO place, String user_id, String custom_name) throws ParseException{
			customInfoService.addCustom(place,user_id, custom_name );
			return true;
	}
	
	//커스텀 삭제 
	/*
	 * parameter: id, uesr_id, custom_name
	 */
	@RequestMapping(value="/deletecustom", method={RequestMethod.POST, RequestMethod.GET})
	public boolean deleteCustom(String id, String user_id, String custom_name, HttpServletRequest req) throws ParseException{
			customInfoService.deleteCustom(id,user_id,custom_name);
			return true;
	}
	


}
