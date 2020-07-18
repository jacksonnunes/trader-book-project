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
@Table(name = "com_competitions")
public class Competition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "com_nome", length = 25, nullable = false)
	@NotEmpty(message = "O preenchimento é obrigatório.")
	@NotNull(message = "O preenchimento é obrigatório.")
	@Length(min = 3, max = 25, message = "O nome da competição deve conter entre 3 e 25 caracteres.")
	private String name;
	
	@NotNull(message = "Escolher país.")
	@ManyToOne
	@JoinColumn(name = "cnt_id")
	private Country country;
	
	@NotNull(message = "Escolher esporte.")
	@ManyToOne
	@JoinColumn(name = "spo_id")
	private Sport sport;
	
	@OneToMany(mappedBy = "competition", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Operation> operation;
	
	public Competition() {
		this.country = new Country();
		this.sport = new Sport();
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public List<Operation> getOperation() {
		return operation;
	}

	public void setOperation(List<Operation> operation) {
		this.operation = operation;
	}

}
