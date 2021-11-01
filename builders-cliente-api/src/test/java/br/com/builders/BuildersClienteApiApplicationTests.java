package br.com.builders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.builders.api.controller.ClienteController;
import br.com.builders.api.model.request.ClienteRequest;
import br.com.builders.domain.service.ClienteService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {
    ClienteController.class
})
class BuildersClienteApiApplicationTests {

    private MockMvc mockMvc;
	
    @MockBean
	private ClienteController clienteController;
	
	@MockBean
	private ClienteService clienteService;
	
	private ObjectMapper objectMapper;
	
	public void setUp() {
        
        mockMvc = MockMvcBuilders
                .standaloneSetup(clienteController)
                .build();
    
    }
    
    @Test
	void deve_cadastrar_cliente_201 () {
    	setUp();
    	
    	  ClienteRequest clienteRequest = new ClienteRequest();
          clienteRequest.setNome("Celso de Oliveira");
          clienteRequest.setEmail("celso.a.oliveira@gmail.com");
          clienteRequest.setTelefone("21977777777");
          clienteRequest.setDataNascimento("1981-05-09");
          
          objectMapper = new ObjectMapper();
          
		try {
			MockHttpServletResponse response = mockMvc.perform(
                 post("/clientes/cadastrar")
                 .content(objectMapper.writeValueAsString(clienteRequest))
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON)
             )
             .andReturn()
             .getResponse();

			assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
			
		} catch (Exception e) {
			assertTrue(false);
			
		}
	}
    
    @Test
	void deve_alterar_cliente_200 () {
    	setUp();
    	
    	  ClienteRequest clienteRequest = new ClienteRequest();
          clienteRequest.setNome("Celso Andre de Oliveira");
          clienteRequest.setEmail("celso.a.oliveira@gmail.com");
          clienteRequest.setTelefone("21977777777");
          clienteRequest.setDataNascimento("1981-05-09");
          
          objectMapper = new ObjectMapper();
          
		try {
			MockHttpServletResponse response = mockMvc.perform(
                 put("/clientes/1/atualizar")
                 .content(objectMapper.writeValueAsString(clienteRequest))
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON)
             )
             .andReturn()
             .getResponse();

			assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
			
		} catch (Exception e) {
			assertTrue(false);
			
		}
	}
    
	@Test
	void deve_buscar_ListaClientes_200 () {
		setUp();
		try {
			MockHttpServletResponse response = mockMvc.perform(
                 get("/clientes?page=0&size=4").accept(MediaType.APPLICATION_JSON)
             )
             .andReturn()
             .getResponse();

			assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
			
		} catch (Exception e) {
			assertFalse(true);
			
		}
	}
	
	@Test
	void deve_buscar_clientes_200 () {
		setUp();
		try {
			MockHttpServletResponse response = mockMvc.perform(
                 get("/clientes/1").accept(MediaType.APPLICATION_JSON)
             )
             .andReturn()
             .getResponse();

			assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
			
			
		} catch (Exception e) {
			assertTrue(false);
			
		}
	}
	
	@Test
    public void deve_deletar_cliente_204() throws Exception {

		setUp();
		try {
			MockHttpServletResponse response = mockMvc.perform(
                 delete("/clientes/1/remover").accept(MediaType.APPLICATION_JSON)
             )
             .andReturn()
             .getResponse();

			assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
			
			
		} catch (Exception e) {
			assertTrue(false);
			
		}
    }
}
