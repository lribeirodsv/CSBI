package com.lribeiro.csbi.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Sempre importar o javax
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity 																				//Indica que deve-se criar uma tabela para esta classe
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 																				//Indica qual será a chave primária
	@GeneratedValue(strategy=GenerationType.IDENTITY) 									//Indica que a chave primária será gerada automaticamente baseado na estratégia
	private Integer id;
	private String nome;

	@JsonManagedReference																//Indica que os objetos devem ser buscados neste lado da relação para evitar o loop 
	@ManyToMany(mappedBy="categorias") 													//Indica que esta relação n/n ja foi mapeada no atributo "categorias"
	private List<Produto> produtos = new ArrayList<>();
	
	public Categoria() {
	}
	
	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
