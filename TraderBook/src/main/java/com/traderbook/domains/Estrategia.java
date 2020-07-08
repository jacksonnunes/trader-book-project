package com.traderbook.domains;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "str_strategies")
public class Estrategia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "str_nome", length = 20, nullable = false)
	@NotEmpty(message = "O preenchimento é obrigatório.")
	@NotNull(message = "O preenchimento é obrigatório.")
	@Length(min = 3, max = 20, message = "O nome da estratégia precisa conter entre 3 e 20 caracteres.")
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "Escolher mercado.")
	@JoinColumn(name = "mkt_id")
	private Mercado estrategiaMercado;
	
	@OneToMany(mappedBy = "estrategia", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Operacao> operacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Mercado getEstrategiaMercado() {
		return estrategiaMercado;
	}

	public void setEstrategiaMercado(Mercado estrategiaMercado) {
		this.estrategiaMercado = estrategiaMercado;
	}

	public List<Operacao> getOperacao() {
		return operacao;
	}

	public void setOperacao(List<Operacao> operacao) {
		this.operacao = operacao;
	}

}
