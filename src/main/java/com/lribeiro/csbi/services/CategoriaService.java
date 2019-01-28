package com.lribeiro.csbi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lribeiro.csbi.domain.Categoria;
import com.lribeiro.csbi.repositories.CategoriaRepository;
import com.lribeiro.csbi.services.exceptions.DataIntegrityException;
import com.lribeiro.csbi.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired 																					//Dependencia sera automaticamente instanciada pelo Spring pelo mencanismo de injeção de dependencias
	private CategoriaRepository repoCategoria;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repoCategoria.findById(id); 									//Optional é um objeto container que vai carregar um objeto do tipo Categoria e encapsula o objeto ter sido instanciado ou nao
		return obj.orElseThrow(() -> new ObjectNotFoundException( 						  		//Caso encontre a categoria a partir do id, retorna o objeto, caso nao encontre, retorna null   
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));		//Chamo a excessão personalizada e passo a mensagem
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repoCategoria.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repoCategoria.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repoCategoria.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			Optional<Categoria> obj = repoCategoria.findById(id);
			throw new DataIntegrityException("Não é possível excluir a categoria " + "'" + obj.get().getNome() + "'" + " pois a mesma possui produtos");
		}
	}

	public List<Categoria> findAll() {
		return repoCategoria.findAll();
	}
}

