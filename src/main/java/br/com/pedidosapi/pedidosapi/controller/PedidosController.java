package br.com.pedidosapi.pedidosapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pedidosapi.pedidosapi.dto.PedidoDto;
import br.com.pedidosapi.pedidosapi.dto.StatusDto;
import br.com.pedidosapi.pedidosapi.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {
	
	@Autowired
	private PedidoService pedidoService;
	
	//Listar
    @GetMapping()
    public List<PedidoDto> listarTodos() {
        return pedidoService.listarPedidos();
    }
    
    //Buscar por ID
    @GetMapping("/{id}")
    public PedidoDto buscarPedidoPorId(@PathVariable Long id) {
        return pedidoService.buscarPedidoPorId(id);
    }
    
    //Cadastrar
    @PostMapping()
    public ResponseEntity<PedidoDto> realizaPedido(@RequestBody PedidoDto pedidoDto, UriComponentsBuilder uriBuilder) {
        
    	PedidoDto pedidoRealizado = pedidoService.criarPedido(pedidoDto);
        URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoRealizado.getId()).toUri();
        return ResponseEntity.created(endereco).body(pedidoRealizado);
    }
	
    //Alterar
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> alterarStatusDoPedido(@PathVariable Long id, @RequestBody StatusDto statusDto){
    	
    	PedidoDto pedidoDtoNaoEncontrado = new PedidoDto();
    	
    	try {
    		PedidoDto pedidoDto = pedidoService.alterarStatus(id, statusDto);
    		return ResponseEntity.ok(pedidoDto);
    	}catch(Exception e) {
    		return ResponseEntity.ok(pedidoDtoNaoEncontrado);
    	}
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPedido(@PathVariable Long id) {
    	
    	String json = "{\"status\":\"Pedido nao encontrado\"}";
    	
    	try {
    		List<PedidoDto> lista = pedidoService.deletarPedido(id);
    		return ResponseEntity.ok("{\"status\":\"Pedido deletado\"}");
    	} catch(Exception e) {
    		return ResponseEntity.ok(json);
    	}
    	
   
    }
}
