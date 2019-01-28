package com.lribeiro.csbi.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lribeiro.csbi.domain.Categoria;
import com.lribeiro.csbi.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService servCategoria;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)  			//endpoint sera /categorias/id sendo que id é um parametro
	public ResponseEntity<?> listar(@PathVariable Integer id) {       	//armazena informacoes de uma resposta HTTP para um servico REST
		
		Categoria obj = servCategoria.buscaCategoria(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)							//define o metodo como POST
	public ResponseEntity<Void> Insert(@RequestBody Categoria obj){		
		obj = servCategoria.insert(obj);								//apos a insersao retorna o objeto
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()		//cria a uri do novo objeto inserido já com sua nova id gerada
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
