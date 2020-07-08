package com.traderbook.domains;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "mkt_markets")
public class Mercado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "mkt_nome", length = 20, nullable = false)
	@NotEmpty(message = "O preenchimento é obrigatório.")
	@NotNull(message = "O preenchimento é obrigatório.")
	@Length(min = 3, max = 20, message = "O nome do mercado precisa conter entre 3 e 20 caracteres.")
	private String mercado;
	
	@OneToMany(mappedBy = "estrategiaMercado")
	private List<Estrategia> estrategias;

	@OneToMany(mappedBy = "mercado", cascade = CascadeType.MERGE)
	private List<Operacao> operacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMercado() {
		return mercado;
	}

	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

	public List<Estrategia> getEstrategias() {
		return estrategias;
	}

	public void setEstrategias(List<Estrategia> estrategias) {
		this.estrategias = estrategias;
	}

	public List<Operacao> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(List<Operacao> operacao) {
		this.operacoes = operacao;
	}

}
