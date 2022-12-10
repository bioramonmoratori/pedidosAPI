package br.com.pedidosapi.pedidosapi;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pedidosapi.pedidosapi.dto.PedidoDto;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PedidosControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deveReceberCodigo200ParaRequisicaoDaListaDePedidos() throws Exception {
		
		URI uri = new URI("/pedidos");
		
		//mockMvc.perform(MockMvcRequestBuilders).post(uri).content(content)
		
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(
				MockMvcResultMatchers.status().is(200));
		
	}
	
	@Test
	public void deveReceberCodigo200ParaRequisicaoDeBuscarPorId() throws Exception {
		
		URI uri = new URI("/pedidos/1");
		
		//mockMvc.perform(MockMvcRequestBuilders).post(uri).content(content)
		
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(
				MockMvcResultMatchers.status().is(200));
		
	}
	
	@Test
	public void deveReceberCodigo200ParaRequisicaoDeCriacaoDeProduto() throws Exception {
		
		URI uri = new URI("/pedidos");
		String json = "{\"descricao\":\"Produto JUnit\", \"valor\":\"50.00\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
		        .content(json)
		        .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().is(201));
		
		//mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(
				//MockMvcResultMatchers.status().is(200));
		
	}
	
	@Test
	public void deveReceberCodigo200ParaAlterarStatusDoPedidoAtravesDoId() throws Exception {
		
		URI uri = new URI("/pedidos/0");
		String json = "{\"status\":\"CANCELADO\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.put(uri)
		        .content(json)
		        .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().is(200));
		
		//mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(
				//MockMvcResultMatchers.status().is(200));
		
	}
	
	@Test
	public void deveReceberCodigo200ParaDeletarPedidoAtravesDoId() throws Exception {
		
		URI uri = new URI("/pedidos/0");
		String json = "";
		
		mockMvc.perform(MockMvcRequestBuilders.delete(uri)
		        .content(json)
		        .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().is(200));
		
		//mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(
				//MockMvcResultMatchers.status().is(200));
		
	}
}
