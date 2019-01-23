package com.lribeiro.csbi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.lribeiro.csbi.domain.Pedido;
import com.lribeiro.csbi.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService servPedido;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)  			//endpoint sera /pedidos/id sendo que id é um parametro
	public ResponseEntity<?> listar(@PathVariable Integer id) {       	//armazena informacoes de uma resposta HTTP para um servico REST
		
		Pedido obj = servPedido.buscaPedido(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
