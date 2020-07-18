package com.traderbook.domains;

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
@Table(name = "con_continents")
public class Continent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "con_name", nullable = false)
	@NotEmpty(message = "Necessário o preenchimento deste campo.")
	@NotNull(message = "Necessário o preenchimento deste campo.")
	@Length(max = 30)
	private String name;
	
	@OneToMany(mappedBy = "continent")
	private Set<Country> countries;

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

	public Set<Country> getCountries() {
		return countries;
	}

	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}

}
