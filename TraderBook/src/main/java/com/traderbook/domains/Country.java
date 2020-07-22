package com.traderbook.domains;

import java.util.LinkedList;
import java.util.List;

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
@Table(name = "cnt_countries")
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cnt_nome", nullable = false)
	@NotEmpty(message = "O preenchimento é obrigatório.")
	@NotNull(message = "O preenchimento é obrigatório.")
	@Length(min = 3, max = 20, message = "O nome do país deve conter entre 3 e 20 caracteres.")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull(message = "Selecionar continente.")
	@JoinColumn(name = "con_id")
	private Continent continent;
	
	@OneToMany(mappedBy = "country")
	private List<Competition> competitions;
	
	@OneToMany(mappedBy = "country")
	private List<Operation> operations;
	
	@OneToMany(mappedBy = "country")
	private List<Users> user;
	
	public Country() {
		this.continent = new Continent();
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

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	public List<Competition> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(List<Competition> competitions) {
		this.competitions = competitions;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public List<Users> getUser() {
		return user;
	}

	public void setUser(List<Users> user) {
		this.user = user;
	}
	
	public List<Competition> getCompetitionsBySport(String sportName) {
		List<Competition> competitions = this.getCompetitions();
		List<Competition> competitionsBySport = new LinkedList<Competition>();
		for(Competition competition : competitions) {
			if(competition.getSport().getName() == sportName) {
				competitionsBySport.add(competition);
			}
		}
		return competitionsBySport;
	}

}
