package com.github.supercodingspring.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class AirReservationControllerSpringTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Find Airline Tickets 성공")
    @Test
    void FindAirlineTickets() throws Exception {
        // given
        Integer userId = 5;
        String ticketType = "왕복";

        // when & then
        String content = mockMvc.perform(
                get("/v1/api/air-reservation/tickets")
                        .param("user-Id", userId.toString())
                        .param("airline-ticket-type", ticketType)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        log.info("결과: " + content);
    }

    @DisplayName("Find Airline Tickets 실패 1")
    @Test
    void FindAirlineTicketsCase2() throws Exception {
        // given
        Integer userId = 5;
        String ticketType = "왕";

        // when & then
        String content = mockMvc.perform(
                                        get("/v1/api/air-reservation/tickets")
                                                .param("user-Id", userId.toString())
                                                .param("airline-ticket-type", ticketType)
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().is4xxClientError())
                                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        log.info("결과: " + content);
    }
}