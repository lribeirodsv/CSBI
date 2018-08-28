package com.lribeiro.csbi.domain.enums;

public enum TipoCliente {
	
	PESSOA_FISICA(1, "Pessoa Física"),
	PESSOA_JURIDICA(2, "Pessoa Jurídica");
	
	private int codigo;
	private String descricao;
		
	private TipoCliente(int codigo, String descricao) {								//Construtor de enumerate é privado
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer codigo) {								//static pois a operação poderá ser executada mesmo sem o objeto estar instanciado
		
		if(codigo == null) return null;
		
		for(TipoCliente x : TipoCliente.values()) {									//percorre todos os valores possíveis do TipoCliente, neste caso pessoa fisica e pessoa juridica 
			if(codigo.equals(x.getCodigo())) {										//se o enumerate que eu estou varrendo for igual ao codigo que eu enviei, eu retorno o objeto enumerado
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido:" + codigo);
	}
}
