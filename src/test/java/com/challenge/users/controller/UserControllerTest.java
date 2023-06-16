package com.challenge.users.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.challenge.users.utils.TestUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@DisplayName("User registration with correct data")
	@Test
	void sing_up_returnOk() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.SING_UP))
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								TestUtils.buildUserSingUpRequestDtoOk())))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.isActive").value(true));
	}

	@DisplayName("User registration with incorrect email")
	@Test
	void sing_up_email_incorrect_returnError() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.SING_UP))
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								TestUtils.buildUserSingUpRequestDtoEmailIncorrect())))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error[0].codigo").value(400));
	}

	@DisplayName("User registration with incorrect password")
	@Test
	void sing_up_password_incorrect_returnError() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.SING_UP))
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper
								.writeValueAsString(TestUtils
										.buildUserSingUpRequestDtoPasswordIncorrect())))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error[0].codigo").value(400));
	}

	@DisplayName("User registration with incorrect email and password")
	@Test
	void sing_up_email_password_incorrect_returnError() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.SING_UP))
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper
								.writeValueAsString(TestUtils
										.buildUserSingUpRequestDtoEmailAndPasswordIncorrect())))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error[0].codigo").value(400))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error[1].codigo").value(400));
	}

	@DisplayName("User registration with bad request")
	@Test
	void sing_up_body_incorrect_returnError() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.SING_UP))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error[0].codigo").value(400));

	}

	@DisplayName("Existing user registration")
	@Test
	void sing_up_existing_user_returnError() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.SING_UP))
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								TestUtils.buildUserSingUpRequestDtoOk2())))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.isActive").value(true));
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.SING_UP))
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								TestUtils.buildUserSingUpRequestDtoOk2())))
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error[0].codigo").value(409));
	}

	@DisplayName("User singup ok,login ok, login not found,login Corrupt token")
	@Test
	void user_login_returnOk() throws Exception {
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.SING_UP))
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								TestUtils.buildUserSingUpRequestDtoOk3())))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		ObjectMapper mapper = new ObjectMapper();
		String result = mvcResult.getResponse().getContentAsString();
		JsonNode node = mapper.readTree(result);
		String token = node.get("token").asText();
		String id = node.get("id").asText();
		// Login Ok
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.LOGIN))
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
				.andReturn();
		// Login not found
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI)
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isNotFound())
				.andReturn();
		// Login Corrupt token
		token = token.substring(10);
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.LOGIN))
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isUnauthorized()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error[0].codigo").value(401))
				.andReturn();
	}

	@DisplayName("not authorized")
	@Test
	void not_found_returnOk() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI))
				.andExpect(status().isUnauthorized())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error[0].codigo").value(401));
	}

	@DisplayName("User registration with correct data equals phones request")
	@Test
	void sing_up_returnOk_equals_phones() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.SING_UP))
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								TestUtils.buildUserSingUpRequestDtoEqualsPhones())))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.isActive").value(true));
	}

	@DisplayName("User registration with bad request")
	@Test
	void sing_up_bad_request_paramet() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.SING_UP))
						.contentType(MediaType.APPLICATION_JSON)
						.content(TestUtils.badrequest()))
				.andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error[0].codigo").value(400));
	}

	@DisplayName("User  multiple singup ok,login ok, login not found,login Corrupt token")
	@Test
	void user_login_concurent_returnOk() throws Exception {
		MvcResult mvcResult_1 = mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.SING_UP))
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								TestUtils.buildUserSingUpRequestDtoOk4())))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		MvcResult mvcResult_2 = mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.SING_UP))
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								TestUtils.buildUserSingUpRequestDtoOk5())))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		ObjectMapper mapper_1 = new ObjectMapper();
		String result_1 = mvcResult_1.getResponse().getContentAsString();
		JsonNode node_1 = mapper_1.readTree(result_1);
		String token_1 = node_1.get("token").asText();
		String id_1 = node_1.get("id").asText();

		ObjectMapper mapper_2 = new ObjectMapper();
		String result_2 = mvcResult_2.getResponse().getContentAsString();
		JsonNode node_2 = mapper_2.readTree(result_2);
		String token_2 = node_2.get("token").asText();
		String id_2 = node_2.get("id").asText();
		// Login 1 Ok
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.LOGIN))
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " + token_1))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id_1))
				.andReturn();
		// Login 2 Ok
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						IUserController.URI.concat(
								IUserController.LOGIN))
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " + token_2))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id_2))
				.andReturn();

	}
}