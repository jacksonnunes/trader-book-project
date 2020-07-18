package com.traderbook.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "dep_deposits")
public class Deposit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "dep_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Campo obrigatório.")
	private Date date;
	
	@Column(name = "dep_value")
	@Positive(message = "O valor do depósito não pode ser negativo.")
	@NotNull
	private double value;
		
	@NotNull(message = "Preencher campo.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ban_id")
	private Bank bank;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usr_id")
	private Users user;
	
	public Deposit() {	
		this.date = new Date();
		this.bank = new Bank();
		this.user = new Users();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
