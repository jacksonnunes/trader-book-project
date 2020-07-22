package com.traderbook.domains;

import java.util.List;
import java.util.Set;

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
@Table(name = "spo_sports")
public class Sport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "spo_name", nullable = false)
	@NotNull(message = "Escolha o nome do esporte.")
	@NotEmpty(message = "Escolha o nome do esporte.")
	@Length(max = 35, message = "O nome do esporte deve conter, no máximo, 35 caracteres.")
	private String name;
	
	@OneToMany(mappedBy = "sport")
	private Set<Competition> competitions;
	
	@OneToMany(mappedBy = "sport")
	private Set<Market> markets;
	
	@OneToMany(mappedBy = "sport")
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

	public Set<Competition> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(Set<Competition> competitions) {
		this.competitions = competitions;
	}

	public Set<Market> getMarkets() {
		return markets;
	}

	public void setMarkets(Set<Market> markets) {
		this.markets = markets;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

}
