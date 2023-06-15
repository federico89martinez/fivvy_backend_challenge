package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;


import com.example.demo.constants.FivvyConstans;
import com.example.demo.controller.FivvyController;
import com.example.demo.service.FivvyService;

@RunWith(SpringRunner.class)
@WebMvcTest(FivvyController.class)
class DemoApplicationTests {

	@MockBean FivvyService fivvyService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCreateText() throws Exception {
		String requestBody = FivvyConstans.REQUEST_BODY;
		this.mockMvc.perform(MockMvcRequestBuilders.post(FivvyConstans.POST_DISCLAIMER)
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestBody)
		  )
		  .andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGetText() throws Exception{

		List<Document> listDocuments = new ArrayList<>();
		Document  document = new Document(FivvyConstans.ID, FivvyConstans.ID_TEST)
		  .append(FivvyConstans.NAME, FivvyConstans.NAME_TEST)
		  .append(FivvyConstans.TEXT, FivvyConstans.TEXT_TEST)
		  .append(FivvyConstans.VERSION,FivvyConstans.VERSION_TEST)
		  .append(FivvyConstans.CREATE_AT, FivvyConstans.CREATE_AT_TEST)
		  .append(FivvyConstans.UPDATE_AT, FivvyConstans.UPDATE_AT_TEST);
		listDocuments.add(document);

		when(fivvyService.getDisclaimer(FivvyConstans.TEXT_TEST)).thenReturn(listDocuments);
		this.mockMvc.perform(MockMvcRequestBuilders.get(FivvyConstans.GET_DISCLAIMER)
			.param(FivvyConstans.TEXT,FivvyConstans.TEXT_TEST)
			.contentType(MediaType.APPLICATION_JSON))
		  .andExpect(MockMvcResultMatchers.status().isOk())
		  .andExpect(MockMvcResultMatchers.jsonPath("$[0]." + FivvyConstans.ID).value(FivvyConstans.ID_TEST))
		  .andExpect(MockMvcResultMatchers.jsonPath("$[0]." + FivvyConstans.NAME).value(FivvyConstans.NAME_TEST))
		  .andExpect(MockMvcResultMatchers.jsonPath("$[0]." + FivvyConstans.TEXT).value(FivvyConstans.TEXT_TEST))
		  .andExpect(MockMvcResultMatchers.jsonPath("$[0]." + FivvyConstans.VERSION).value(FivvyConstans.VERSION_TEST))
		  .andExpect(MockMvcResultMatchers.jsonPath("$[0]." + FivvyConstans.CREATE_AT).value(FivvyConstans.CREATE_AT_TEST))
		  .andExpect(MockMvcResultMatchers.jsonPath("$[0]." + FivvyConstans.UPDATE_AT).value(FivvyConstans.UPDATE_AT_TEST));

	}
}
