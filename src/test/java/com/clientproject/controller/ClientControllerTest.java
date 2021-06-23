package com.clientproject.controller;

import com.clientproject.model.Client;
import com.clientproject.repository.ClientRepository;
import com.clientproject.service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ClientController clientController;

    @MockBean
    private ClientRepository repository;

    @MockBean
    private ClientService service;

    @Test
    void createClient() throws Exception {
        String jsonString  = "{\n" +
                "    \"firstName\" : \"harish\",\n" +
                "    \"lastName\"  : \"kumar\",\n" +
                "    \"mobileNumber\" : \"1234567890\",\n" +
                "    \"idNumber\" : \"2001015800085\",\n" +
                "    \"physicalAddress\" : \"192.13.12.90\"\n" +
                "}";
        Client item = new Client("harish", "kumar","1234567890","2001015800085", "192.13.12.90");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/client/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isCreated());
    }
}