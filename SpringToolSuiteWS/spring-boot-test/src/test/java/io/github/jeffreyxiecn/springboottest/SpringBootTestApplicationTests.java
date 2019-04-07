package io.github.jeffreyxiecn.springboottest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import io.github.jeffreyxiecn.springboottest.dao.EmployeeRepository;
import io.github.jeffreyxiecn.springboottest.entity.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = {SpringBootTestApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
// @AutoConfigureTestDatabase
public class SpringBootTestApplicationTests {

  @Autowired private MockMvc mvc;

  @Autowired private EmployeeRepository repository;

  @After
  public void resetDb() {
    repository.deleteAll();
  }

  @Test
  public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
    String firstEmpName = "bob";
    String secondEmpName = "alex";
    createTestEmployee(firstEmpName);
    createTestEmployee(secondEmpName);

    mvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name", is(firstEmpName)))
        .andExpect(jsonPath("$[1].name", is(secondEmpName)));
  }

  private void createTestEmployee(String name) {
    Employee emp = new Employee(name);
    repository.saveAndFlush(emp);
  }
}
