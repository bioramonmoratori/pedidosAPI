package br.com.pedidosapi.pedidosapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.stereotype.Service;

import br.com.pedidosapi.pedidosapi.dto.PedidoDto;
import br.com.pedidosapi.pedidosapi.dto.StatusDto;
import br.com.pedidosapi.pedidosapi.enums.Status;
import br.com.pedidosapi.pedidosapi.models.Pedido;
import br.com.pedidosapi.pedidosapi.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<PedidoDto> listarPedidos(){
		
		List<Pedido> listaDePedidos = pedidoRepository.findAll();
		List<PedidoDto> listaDePedidosDto = new ArrayList<PedidoDto>();
		
		for(int i = 0; i < listaDePedidos.size(); i++)
		{
			PedidoDto pedidoDto = new PedidoDto();
			pedidoDto.converter(listaDePedidos.get(i));
		    listaDePedidosDto.add(pedidoDto);
		}
		
		return listaDePedidosDto;
	}

	public PedidoDto criarPedido(PedidoDto pedidoDto) {
		
		Pedido pedido = new Pedido();
		
		pedido.setDescricao(pedidoDto.getDescricao());
		pedido.setValor(pedidoDto.getValor());
		pedido.setDataDeCriacao(LocalDateTime.now());
		pedido.setStatus(Status.CRIADO);
		
		pedidoRepository.save(pedido);
		
		pedidoDto.setDataDeCriacao(pedido.getDataDeCriacao());
		pedidoDto.setId(pedido.getId());
		pedidoDto.setStatus(pedido.getStatus());
		
		return pedidoDto;
	}

	public PedidoDto buscarPedidoPorId(Long id) {
		
		Optional<Pedido> pedidoOptional;
		Pedido pedido;
		PedidoDto pedidoDto = new PedidoDto();
		
		try {
			pedidoOptional = pedidoRepository.findById(id);
			pedido = pedidoOptional.get();
			pedidoDto.converter(pedido);
			
			return pedidoDto;
			
		} catch(Exception e) {
			return pedidoDto;
		}
	}
	
	public PedidoDto alterarStatus(Long id, StatusDto status) {
		
		
		Pedido pedido = pedidoRepository.findById(id).get();
		pedidoRepository.alteraStatus(status.getStatus(), pedido);

		PedidoDto pedidoDto = new PedidoDto();
		
		try {
			Pedido pedidoAtualizado = pedidoRepository.findById(id).get();
			pedidoDto.converter(pedidoAtualizado);
			return pedidoDto;
		} catch(Exception e) {
			throw e;
		}
		
	}
	
	public List<PedidoDto> deletarPedido(Long id) {
		try {
			pedidoRepository.deleteById(id);	
			return this.listarPedidos();
			
		} catch(Exception e) {
			throw e;
		}
	}
	
}
