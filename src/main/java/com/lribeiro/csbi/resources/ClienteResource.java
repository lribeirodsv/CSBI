package com.lribeiro.csbi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lribeiro.csbi.domain.Cliente;
import com.lribeiro.csbi.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService servCliente;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)  			//endpoint sera /clientes/id sendo que id é um parametro
	public ResponseEntity<Cliente> listar(@PathVariable Integer id) {       	//armazena informacoes de uma resposta HTTP para um servico REST
		
		Cliente obj = servCliente.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
