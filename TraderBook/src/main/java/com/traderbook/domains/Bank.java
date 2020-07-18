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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "ban_banks")
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ban_name", nullable = false)
	@NotNull(message = "O preenchimento é obrigatório.")
	@NotEmpty(message = "O preenchimento é obrigatório.")
	@Length(min = 3, max = 15, message = "O nome da banca precisa conter entre 3 e 15 caracteres.")
	private String name;
	
	@Column(name = "ban_currency", nullable = false)
	@NotNull(message = "O preenchimento é obrigatório.")
	@NotEmpty(message = "O preenchimento é obrigatório.")
	private String currency;
	
	@Column(name = "ban_saldo", precision = 2)
	@Min(value = 0, message = "O valor não pode ser negativo.")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,###.##")
	private double balance;
	
	@OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
	private List<Deposit> deposits;
	
	@OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
	private List<Withdraw> withdraws;
	
	@OneToMany(mappedBy = "bank", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Operation> operations;
	
	@ManyToOne
	@JoinColumn(name = "id_usr")
	private Users user;
	
	public Bank() {
		this.user = new Users();
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Deposit> getDeposits() {
		return deposits;
	}

	public void setDeposits(List<Deposit> deposits) {
		this.deposits = deposits;
	}

	public List<Withdraw> getWithdraws() {
		return withdraws;
	}

	public void setWithdraws(List<Withdraw> withdraws) {
		this.withdraws = withdraws;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public void deposit(double dapositValue) {
		double newValue = this.balance + dapositValue;
		this.setBalance(newValue);
	}
	
	public void withdraw(double withdrawValue) {
		double newValue = this.balance - withdrawValue;
		this.setBalance(newValue);
	}

}
