package mx.tec.lab.rest;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class GreetingRestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void givenARequest_WhenEmptyName_thenHelloWorld() throws Exception {
		this.mockMvc.perform(get("/greeting"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("content", equalTo("Hello, World!")));		
	}
	
	@Test
	public void givenARequest_whenProvidedName_thenHelloName() throws Exception {
		this.mockMvc.perform(get("/greeting?name=Jon"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("content", equalTo("Hello, Jon!")));		
	}
	
	@Test
	public void givenARequest_whenNullName_thenHelloWorld() throws Exception {
		this.mockMvc.perform(get("/greeting?name="))
		.andExpect(status().isOk())
		.andExpect(jsonPath("content", equalTo("Hello, World!")));		
	}
	
	@Test
	public void givenARequest_whenWrongURL_thenError404() throws Exception {
		this.mockMvc.perform(get("/greting"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void givenARequest_whenProvidedName_thenByeName() throws Exception {
		this.mockMvc.perform(get("/goodbye?name=Cersei"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("content", equalTo("Bye, Cersei!")));		
	}
	
	@Test
	public void givenARequest_whenNullName_thenByeWorld() throws Exception {
		this.mockMvc.perform(get("/goodbye?name="))
		.andExpect(status().isOk())
		.andExpect(jsonPath("content", equalTo("Bye, World!")));		
	}
}