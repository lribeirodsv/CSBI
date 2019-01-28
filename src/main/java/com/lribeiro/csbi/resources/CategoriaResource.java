package com.lribeiro.csbi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lribeiro.csbi.domain.Categoria;
import com.lribeiro.csbi.dto.CategoriaDTO;
import com.lribeiro.csbi.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService servCategoria;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)  			//endpoint sera /categorias/id sendo que id é um parametro
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {       	//armazena informacoes de uma resposta HTTP para um servico REST
		
		Categoria obj = servCategoria.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)							//define o metodo como POST
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){		
		obj = servCategoria.insert(obj);								//apos a insersao retorna o objeto
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()		//cria a uri do novo objeto inserido já com sua nova id gerada
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = servCategoria.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		servCategoria.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)  							//endpoint sera /categorias
	public ResponseEntity<List<CategoriaDTO>> findAll() {       			
		
		List<Categoria> list = servCategoria.findAll();
		
		/*
		stream  = metodo que percorre uma lista
		map     = realiza uma operacao para cada elemento da lista
		->      = funcao anonima
		collect = volta a colecao de objetos pro tipo lista
		*/
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
}
