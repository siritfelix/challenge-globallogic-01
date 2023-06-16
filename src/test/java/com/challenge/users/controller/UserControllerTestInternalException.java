package com.challenge.users.controller;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.challenge.users.repository.UserRepository;
import com.challenge.users.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTestInternalException {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserRepository userRepository;

    @DisplayName("User registration internal server error")
    @Test
    void sing_up_returnOk() throws Exception {
        doThrow(new NullPointerException()).when(userRepository).findOneByEmail("null");
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        IUserController.URI.concat(
                                IUserController.SING_UP))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                TestUtils.buildUserSingUpRequestDtoOk())))
                .andExpect(status().isInternalServerError());
    }
}
