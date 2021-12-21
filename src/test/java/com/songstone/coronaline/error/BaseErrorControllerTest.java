package com.songstone.coronaline.error;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BaseErrorController.class)
class BaseErrorControllerTest {

    @Autowired
    private MockMvc mvc;

    @DisplayName("[view] [GET] 에러 페이지 - 페이지 없음")
    @Test
    void givenWrongURI_whenRequestingPage_thenReturn404() throws Exception {
        //given

        //when
        ResultActions result = mvc.perform(get("/wrong-uri"));

        //then
        result
                .andExpect(status().isNotFound())
                .andDo(print())
        ;
    }
}