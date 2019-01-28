package com.lribeiro.csbi.services;

import com.lribeiro.csbi.domain.Pedido;
import com.lribeiro.csbi.repositories.PedidoRepository;
import com.lribeiro.csbi.services.exceptions.ObjectNotFoundException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired 																				//Dependencia sera automaticamente instanciada pelo Spring pelo mencanismo de injeção de dependencias
	private PedidoRepository repoPedido;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repoPedido.findById(id); 									//Optional é um objeto container que vai carregar um objeto do tipo Pedido e encapsula o objeto ter sido instanciado ou nao
		return obj.orElseThrow(() -> new ObjectNotFoundException( 						  	//Caso encontre o pedido a partir do id, retorna o objeto, caso nao encontre, retorna null   
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));		//Chamo a excessão personalizada e passo a mensagem
	}	
}
