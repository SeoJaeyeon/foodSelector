package kr.ac.smu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.smu.DTO.CustomPlaceDTO;
import kr.ac.smu.DTO.PlaceDTO;
import kr.ac.smu.service.KakaoLocalAPIService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
)
@WebAppConfiguration
public class CustomInsertTest {

	Logger logger=LoggerFactory.getLogger(FoodSelectorControllerTest.class);
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired 
	private KakaoLocalAPIService kakaoLocalAPIService;
	@Before
	public void before(){
		mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		logger.info("before");
	}
	
	@Test
	public void testKaKaoAPI() throws Exception{
		ResponseEntity<String> place=kakaoLocalAPIService.complete("127", "43");
		Map<Integer, CustomPlaceDTO> place_list=new HashMap<Integer,CustomPlaceDTO>();
		place_list=parse(place);
	
	        this.mockMvc.perform(
	                post("/addcustom")
	                	.contentType(MediaType.APPLICATION_JSON)
	                	.param("place", place.toString())
	                    .param("user_id", "0000")
	                    .param("custom_name","룰룰룰")
	        			.accept(MediaType.APPLICATION_JSON))
	                .andDo(print())
	                .andExpect(status().isOk());        
	}
	@Test
	public void deleteCustomTest() throws Exception{
	        this.mockMvc.perform(
	                post("/deletecustom")
	                	.param("id", "11046641")
	                    .param("user_id", "0000")
	                    .param("custom_name","수빈이최애"))
	                .andDo(print())
	                .andExpect(status().isOk());      
	}
	
	@After
	public void after(){
		logger.info("after");
	}
	
	public Map<Integer, CustomPlaceDTO> parse(ResponseEntity<String> res) throws ParseException, JsonParseException, JsonMappingException, IOException{

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


}
