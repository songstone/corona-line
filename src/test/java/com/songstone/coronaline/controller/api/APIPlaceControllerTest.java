package com.songstone.coronaline.controller.api;

import com.songstone.coronaline.constant.ErrorCode;
import com.songstone.coronaline.constant.PlaceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(APIPlaceController.class)
class APIPlaceControllerTest {

    @Autowired
    private MockMvc mvc;

    @DisplayName("[API] [GET] 장소 리스트 조회")
    @Test
    void givenNothing_whenRequestingPlaces_thenReturnsListOfPlacesInStandardResponse() throws Exception {

        //given

        //when
        ResultActions result = mvc.perform(get("/api/places"));
        //then
        result
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].placeType").value(PlaceType.COMMON.name()))
                .andExpect(jsonPath("$.data[0].placeName").value("송파배드민턴장"))
                .andExpect(jsonPath("$.data[0].address").value("송파구 충민로 155"))
                .andExpect(jsonPath("$.data[0].phoneNumber").value("010-1111-2222"))
                .andExpect(jsonPath("$.data[0].capacity").value(40))
                .andExpect(jsonPath("$.data[0].memo").value("신장개업"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()))
                .andDo(print());
    }

    @DisplayName("[API] [GET] 단일 장소 조회 - 장소 있는 경우")
    @Test
    void givenPlaceAndItsId_whenRequestingPlace_thenReturnsPlaceInStandardResponse() throws Exception {

        //given
        int placeId = 1;
        //when
        ResultActions result = mvc.perform(get("/api/places/" + placeId));
        //then
        result
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data.placeType").value(PlaceType.COMMON.name()))
                .andExpect(jsonPath("$.data.placeName").value("송파배드민턴장"))
                .andExpect(jsonPath("$.data.address").value("송파구 충민로 155"))
                .andExpect(jsonPath("$.data.phoneNumber").value("010-1111-2222"))
                .andExpect(jsonPath("$.data.capacity").value(40))
                .andExpect(jsonPath("$.data.memo").value("신장개업"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()))
                .andDo(print());
    }

    @DisplayName("[API] [GET] 단일 장소 조회 - 장소 없는 경우")
    @Test
    void givenPlaceAndItsId_whenRequestingPlace_thenReturnsEmptyInStandardResponse() throws Exception {

        //given
        int placeId = 2;
        //when
        ResultActions result = mvc.perform(get("/api/places/" + placeId));
        //then
        result
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()))
                .andDo(print());
    }
}