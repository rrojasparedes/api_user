package com.sermaluc.springboot.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.JsonNode;

@SpringBootTest
@WebAppConfiguration
@TestPropertySource(locations = "classpath:application.properties")
class UserControllerTest {

	private final static String BASE_URL = "/api/users/create";
	private final static String CREATE_USER_OK_JSON = "/json/create_user_ok.json";
	private final static String CREATE_USER_INVALID_EMAIL_JSON = "/json/create_user_invalid_email.json";
	private final static String CREATE_USER_INVALID_PASSWORD_JSON = "/json/create_user_invalid_password.json";

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testCreateUserSuccess() throws Exception {
		String json = readJsonFromFile(CREATE_USER_OK_JSON);
		MvcResult result = performPostRequest(json);
		assertEquals(201, result.getResponse().getStatus());
	}

	@Test
	public void testCreateUserInvalidEmailFormat(@Value("${email.invalid}") String emailInvalidMessage)
			throws Exception {
		String json = readJsonFromFile(CREATE_USER_INVALID_EMAIL_JSON);
		MvcResult result = performPostRequest(json);
		assertEquals(emailInvalidMessage, getMessageFromResponse(result));
	}

	@Test
	public void testCreateUserInvalidPasswordFormat(@Value("${password.invalid}") String passwordInvalidMessage)
			throws Exception {
		String json = readJsonFromFile(CREATE_USER_INVALID_PASSWORD_JSON);
		MvcResult result = performPostRequest(json);
		assertEquals(passwordInvalidMessage, getMessageFromResponse(result));
	}

	private String readJsonFromFile(String filePath) throws IOException {
		ClassPathResource resource = new ClassPathResource(filePath);
		return new String(Files.readAllBytes(Paths.get(resource.getURI())));
	}

	private MvcResult performPostRequest(String json) throws Exception {
		return mockMvc.perform(
				MockMvcRequestBuilders.post(BASE_URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andReturn();
	}

	private String getMessageFromResponse(MvcResult result) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode responseJson = objectMapper.readTree(result.getResponse().getContentAsString());
		return responseJson.get("mensaje").asText();
	}
}