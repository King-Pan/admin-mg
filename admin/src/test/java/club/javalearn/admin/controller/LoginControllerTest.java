package club.javalearn.admin.controller;

import club.javalearn.admin.AdminApplicationTests;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class LoginControllerTest extends AdminApplicationTests {


    @Test
    public void loginTest() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.
                    post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("username", "pwpw1218")
                    .param("password", "888888"))
                    .andDo(print()).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
