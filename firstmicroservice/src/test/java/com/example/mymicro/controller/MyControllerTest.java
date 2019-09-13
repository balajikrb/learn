package com.example.mymicro.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.mymicro.beans.MyApp;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyControllerTest {

	@Autowired
	private ApplicationContext context;
	private WebTestClient webTestClient;
	private MyController myController = null;
	private MyController myControllerMock = null;

	@LocalServerPort
	int randomServerPort;

	@Autowired
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		webTestClient = WebTestClient.bindToApplicationContext(this.context).build();
		myController = new MyController();
	}

	@Test public void testGetMethod() {
		webTestClient.get().uri("/myapp").exchange().expectStatus().isOk();
	}

	/*
	 * @Test public void test_getAll_Method() throws URISyntaxException {
	 * 
	 * RestTemplate restTemplate = new RestTemplate(); final String url =
	 * "http://localhost:" + randomServerPort + "/myapp"; URI uri = new URI(url);
	 * 
	 * ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	 * Assert.assertEquals(200, result.getStatusCodeValue()); }
	 */

	@Test
	public void testGetId() throws Exception { 
		
		MyApp myApp = new MyApp();
		myApp.setAddress("Address1");
		myApp.setName("Name1");
		
		List<MyApp> listOfMyApp = new ArrayList<>();
		listOfMyApp.add(myApp);
		
		Mockito.when(myControllerMock.getAll()).thenReturn(listOfMyApp);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/myapp");
		mockMvc.perform(requestBuilder).andExpect(content().json("{\"supi\":\"imsi-10001\",\"suci\":\"dsds\",\"gpsi\":\"566\",\"pei\":\"77732\",\"guami\":\"ds\",\"amfinstanceid\":\"j1\"}"));	
		
	}
}
