package org.closure.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.closure.app.UserModule.controllers.LaongContronller;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(LaongContronller.class)
public class UnitTest  {
    @Autowired
    MockMvc mockMvc;
    @Test
    void LangTest() throws Exception
    {
        
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/en/user/test")).andReturn();
        assertEquals(mvcResult.getResponse().getContentAsString(), "en");
    }
}
