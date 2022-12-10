package br.com.pedidosapi.pedidosapi.dto;

import java.time.LocalDateTime;
import br.com.pedidosapi.pedidosapi.enums.Status;
import br.com.pedidosapi.pedidosapi.models.Pedido;

public class PedidoDto {
	
	private Long id;
	private String descricao;
	private Double valor;
	private LocalDateTime dataDeCriacao;
	private Status status;
	
	//Getters e Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}
	public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	//Converter Model em Dto
	public void converter(Pedido pedido) {
		
		this.id = pedido.getId();
		this.descricao = pedido.getDescricao();
		this.valor = pedido.getValor();
		this.dataDeCriacao = pedido.getDataDeCriacao();
		this.status = pedido.getStatus();
	}
	
}
