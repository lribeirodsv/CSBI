package com.lribeiro.csbi.services;

import com.lribeiro.csbi.domain.Categoria;
import com.lribeiro.csbi.repositories.CategoriaRepository;
import com.lribeiro.csbi.services.exceptions.ObjectNotFoundException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

	@Autowired 																					//Dependencia sera automaticamente instanciada pelo Spring pelo mencanismo de injeção de dependencias
	private CategoriaRepository repoCategoria;
	
	public Categoria buscaCategoria(Integer id) {
		Optional<Categoria> obj = repoCategoria.findById(id); 									//Optional é um objeto container que vai carregar um objeto do tipo Categoria e encapsula o objeto ter sido instanciado ou nao
		return obj.orElseThrow(() -> new ObjectNotFoundException( 						  		//Caso encontre a categoria a partir do id, retorna o objeto, caso nao encontre, retorna null   
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));		//Chamo a excessão personalizada e passo a mensagem
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repoCategoria.save(obj);
	}
}
