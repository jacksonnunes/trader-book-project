package com.traderbook.domains;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Banca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ban_nome_banca", nullable = false)
	@NotNull(message = "O preenchimento é obrigatório.")
	@NotEmpty(message = "O preenchimento é obrigatório.")
	@Length(min = 3, max = 15, message = "O nome da banca precisa conter entre 3 e 15 caracteres.")
	private String nomeBanca;
	
	@Column(name = "ban_saldo", precision = 2)
	@Min(value = 0, message = "O valor não pode ser negativo.")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,###.##")
	private double saldo;
	
	@OneToMany(mappedBy = "banca", fetch = FetchType.LAZY)
	private List<Deposito> depositos;
	
	@OneToMany(mappedBy = "banca", fetch = FetchType.LAZY)
	private List<Saque> saques;
	
	@OneToMany(mappedBy = "bancaOperacao", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Operacao> operacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeBanca() {
		return nomeBanca;
	}

	public void setNomeBanca(String nomeBanca) {
		this.nomeBanca = nomeBanca;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public List<Deposito> getDepositos() {
		return depositos;
	}

	public void setDepositos(List<Deposito> depositos) {
		this.depositos = depositos;
	}

	public List<Saque> getSaques() {
		return saques;
	}

	public void setSaques(List<Saque> saques) {
		this.saques = saques;
	}

	public List<Operacao> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(List<Operacao> operacoes) {
		this.operacoes = operacoes;
	}

}
