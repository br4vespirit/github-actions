package com.dima.githubactions.controller;

import com.dima.githubactions.model.dao.Car;
import com.dima.githubactions.service.CarService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@WebMvcTest
class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carService;

    private final String API_URL = "http://localhost:8080/api/v1/car";

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    void findAll() {
        Mockito.when(carService.findAll()).thenReturn(List.of(new Car(), new Car(), new Car()));
        MvcResult result = mvc.perform(get(API_URL))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        List<Car> carList = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertThat(carList).isNotEmpty();
    }

    @SneakyThrows
    @Test
    void findById() {
        Mockito.when(carService.findById(1L)).thenReturn(null);
        mvc.perform(get(API_URL + "/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @SneakyThrows
    @Test
    void findById_1() {
        Mockito.when(carService.findById(1L)).thenReturn(new Car());
        MvcResult result = mvc.perform(get(API_URL + "/1"))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        Car car = objectMapper.readValue(content, new TypeReference<>() {});
        assertThat(car).isNotNull();
    }

    @SneakyThrows
    @Test
    void save() {
        Mockito.when(carService.save(Mockito.any(Car.class))).thenReturn(new Car());
        MvcResult result = mvc.perform(post(API_URL)
                        .content(objectMapper.writeValueAsString(new Car()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        Car car = objectMapper.readValue(content, new TypeReference<>() {});
        assertThat(car).isNotNull();
    }

    @SneakyThrows
    @Test
    void update() {
        Mockito.when(carService.update(Mockito.eq(1L), Mockito.any(Car.class))).thenReturn(new Car());
        MvcResult result = mvc.perform(patch(API_URL + "/1")
                        .content(objectMapper.writeValueAsString(new Car()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        Car car = objectMapper.readValue(content, new TypeReference<>() {});
        assertThat(car).isNotNull();
    }
}