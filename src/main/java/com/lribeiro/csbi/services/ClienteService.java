package com.lribeiro.csbi.services;

import com.lribeiro.csbi.domain.Cliente;
import com.lribeiro.csbi.repositories.ClienteRepository;
import com.lribeiro.csbi.services.exceptions.ObjectNotFoundException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired 																					//Dependencia sera automaticamente instanciada pelo Spring pelo mencanismo de injeção de dependencias
	private ClienteRepository repoCliente;
	
	public Cliente buscaCliente(Integer id) {
		Optional<Cliente> obj = repoCliente.findById(id); 										//Optional é um objeto container que vai carregar um objeto do tipo Cliente e encapsula o objeto ter sido instanciado ou nao
		return obj.orElseThrow(() -> new ObjectNotFoundException( 						  		//Caso encontre o cliente a partir do id, retorna o objeto, caso nao encontre, retorna null   
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));		//Chamo a excessão personalizada e passo a mensagem
	}	
}
