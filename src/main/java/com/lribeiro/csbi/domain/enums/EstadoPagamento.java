package com.lribeiro.csbi.domain.enums;

public enum EstadoPagamento {

	PENDENTE (1, "Pendente"),
	QUITADO (2, "Quitado"),
	CANCELADO (3, "Cancelado");
	
	private int codigo;
	private String descricao;
		
	private EstadoPagamento(int codigo, String descricao) {									//Construtor de enumerate é privado
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer codigo) {									//static pois a operação poderá ser executada mesmo sem o objeto estar instanciado
		
		if(codigo == null) return null;
		
		for(EstadoPagamento x : EstadoPagamento.values()) {									//percorre todos os valores possíveis do EstadoPagamento, neste caso pendente, quitado e cancelado 
			if(codigo.equals(x.getCodigo())) {												//se o enumerate que eu estou varrendo for igual ao codigo que eu enviei, eu retorno o objeto enumerado
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido:" + codigo);
	}
}
