package kr.ac.smu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
)
@WebAppConfiguration
public class KaKaoAPIServerControllerTest {
	Logger logger=LoggerFactory.getLogger(KaKaoAPIServerControllerTest.class);
	
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	
	@Before
	public void before(){
		mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		logger.info("before");
	}
	
	@Test
	public void testKaKaoAPI() throws Exception{
	        this.mockMvc.perform(
	                get("/crandom")
	                    .param("x", "127.213123")
	                    .param("y", "65.213123"))        
	                .andDo(print())
	                .andExpect(status().isOk());        
	}
	
	@After
	public void after(){
		logger.info("after");
	}
	
}
