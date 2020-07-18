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
public class Market {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "mkt_nome", length = 20, nullable = false)
	@NotEmpty(message = "O preenchimento é obrigatório.")
	@NotNull(message = "O preenchimento é obrigatório.")
	@Length(min = 3, max = 20, message = "O nome do mercado precisa conter entre 3 e 20 caracteres.")
	private String name;
	
	@OneToMany(mappedBy = "market")
	private List<Strategy> strategy;

	@OneToMany(mappedBy = "market", cascade = CascadeType.MERGE)
	private List<Operation> operations;

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

	public List<Strategy> getStrategy() {
		return strategy;
	}

	public void setStrategy(List<Strategy> strategy) {
		this.strategy = strategy;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

}
