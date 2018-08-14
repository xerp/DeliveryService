package org.xerp.deliveryservice.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greeting() throws Exception {
        mockMvc.perform(get("/points/index"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to point RESTful web service"));
    }


    @Test
    public void addPoint() throws Exception {
        mockMvc.perform(post("/points/a"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }


    @Test
    public void deletePoint() throws Exception {
        mockMvc.perform(delete("/points/a"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}