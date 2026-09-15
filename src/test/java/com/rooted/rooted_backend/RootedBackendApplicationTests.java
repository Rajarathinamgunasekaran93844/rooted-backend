package com.rooted.rooted_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class RootedBackendApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void healthAndCatalogEndpointsAreAvailable() throws Exception {
		mockMvc.perform(get("/api/health"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("ok"));

		mockMvc.perform(get("/api/catalog/instruments/yal"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("YĀL"));
	}

	@Test
	void subscriptionRequiresAValidEmail() throws Exception {
		mockMvc.perform(post("/api/subscriptions")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Asha\",\"email\":\"not-an-email\",\"interest\":\"archives\"}"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.fields.email").exists());
	}

	@Test
	void subscriptionIsCreatedAndDuplicateEmailsAreRejected() throws Exception {
		String body = "{\"name\":\"Asha\",\"email\":\"asha@example.com\",\"interest\":\"archives\"}";

		mockMvc.perform(post("/api/subscriptions")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.message").value("Subscription created."));

		mockMvc.perform(post("/api/subscriptions")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.fields.email").exists());
	}

}
