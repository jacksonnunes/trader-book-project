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
public class Strategy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "str_nome", length = 20, nullable = false)
	@NotEmpty(message = "O preenchimento é obrigatório.")
	@NotNull(message = "O preenchimento é obrigatório.")
	@Length(min = 3, max = 20, message = "O nome da estratégia precisa conter entre 3 e 20 caracteres.")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "Escolher mercado.")
	@JoinColumn(name = "mkt_id")
	private Market market;
	
	@OneToMany(mappedBy = "strategy", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Operation> operation;
	
	public Strategy() {
		this.market = new Market();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public List<Operation> getOperation() {
		return operation;
	}

	public void setOperation(List<Operation> operation) {
		this.operation = operation;
	}

}
