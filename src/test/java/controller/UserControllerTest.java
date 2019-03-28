package controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.alvisid.testtaskmt.Application;
import ru.alvisid.testtaskmt.model.User;
import ru.alvisid.testtaskmt.web.UserController;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static testdata.UserTestData.*;


@RunWith(SpringRunner.class)
@DataJpaTest
//@WebMvcTest(value = UserController.class, includeFilters = @ComponentScan.Filter(classes= EnableWebSecurity.class), secure = false)
@SpringBootTest(classes = Application.class)
//@WebAppConfiguration
//@ContextConfiguration(classes={Application.class, SecurityConfiguration.class})
//@SpringBootConfiguration
@BootstrapWith(value = SpringBootTestContextBootstrapper.class)

@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml"
})
//@SpringBootApplication(scanBasePackages = "ru.alvisid.testtaskmt")
@AutoConfigureTestDatabase(replace = NONE)
//@AutoConfigureMockMvc(secure=false)
@EntityScan("ru.alvisid.testtaskmt.model")

public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TestEntityManager entityManager;

    /*@Autowired
    private MockMvc mvc;*/


    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        UserController controller = context.getBean(UserController.class);
    }

    @Autowired
    WebApplicationContext webApplicationContext;

    @Test
    @WithMockUser(username = "ann@list.ru", password = "ann")
    public void getUser() throws Exception {
        User expected = new User(USER_1);

        mvc.perform(get("/api/user/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(expected.getId())))
                .andExpect(jsonPath("$.name", is(expected.getName())))
                .andExpect(jsonPath("$.email", is(expected.getEmail())))
                .andExpect(jsonPath("$.password", is("")))
                .andExpect(jsonPath("$.birthDate[0]", equalTo(expected.getBirthDate().getYear())))
                .andExpect(jsonPath("$.birthDate[1]", equalTo(expected.getBirthDate().getMonthValue())))
                .andExpect(jsonPath("$.birthDate[2]", equalTo(expected.getBirthDate().getDayOfMonth())));
    }

    @Test
    @WithMockUser(username = "ann@list.ru", password = "ann")
    public void getUsers() throws Exception {
        ResultActions resultActions = mvc.perform(get("/api/users"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));

        userInArrayExpect(resultActions, USER_3, USER_2, USER_4, USER_1);
    }

    @Test
    @WithMockUser(username = "ann@list.ru", password = "ann")
    public void addUser() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/user/add")
                .param("name", "Curt")
                .param("email", "curt@ya.ru")
                .param("password", "curt")
                .param("birthDate", "1999-03-20");
        ResultActions resultActions = mvc.perform(builder);
        resultActions
                .andExpect(status().isOk());
    }

    private void userInArrayExpect(ResultActions resultActions, User... users) throws Exception {
        for (int i = 0, end = users.length; i < end; i++) {
            resultActions.andExpect(jsonPath(String.format("$[%d].name", i), is(users[i].getName())))
                    .andExpect(jsonPath(String.format("$[%d].id", i), is(users[i].getId())))
                    .andExpect(jsonPath(String.format("$[%d].email", i), is(users[i].getEmail())))
                    .andExpect(jsonPath(String.format("$[%d].password", i), is("")))
                    .andExpect(jsonPath(String.format("$[%d].birthDate[0]", i), equalTo(users[i].getBirthDate().getYear())))
                    .andExpect(jsonPath(String.format("$[%d].birthDate[1]", i), equalTo(users[i].getBirthDate().getMonthValue())))
                    .andExpect(jsonPath(String.format("$[%d].birthDate[2]", i), equalTo(users[i].getBirthDate().getDayOfMonth())));
        }


    }
}
