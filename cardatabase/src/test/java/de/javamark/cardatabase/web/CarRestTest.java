package de.javamark.cardatabase.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAuthenticationValidCredentials() throws Exception {
        this.mockMvc.perform(post("/login")
                .content("{\"username\":\"user\", \"password\":\"password\"}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void loginWithValidCredentials_FetchResourcesWithJWT() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/login")
                .content("{\"username\":\"user\", \"password\":\"password\"}")).andReturn();

        String authHeader = mvcResult.getResponse().getHeader("Authorization");

        this.mockMvc.perform(get("/api").header("Authorization", authHeader))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void testAuthenticationInvalidCredentials() throws Exception {
        this.mockMvc.perform(post("/login")
                .content("{\"username\":\"unknown\", \"password\":\"blabla\"}"))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
}
