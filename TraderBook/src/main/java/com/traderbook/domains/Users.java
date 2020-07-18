package com.traderbook.domains;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "usr_users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id")
	private Long id;
	
	@Column(name = "usr_name", length = 25, nullable = false)
	@NotEmpty(message = "O preenchimento é obrigatório.")
	@NotNull(message = "O preenchimento é obrigatório.")
	@Length(min = 2, max = 25, message = "Seu nome deve conter entre 2 e 25 caracteres.")
	private String name;
	
	@Column(name = "usr_surname", length = 25, nullable = false)
	@NotEmpty(message = "O preenchimento é obrigatório.")
	@NotNull(message = "O preenchimento é obrigatório.")
	@Length(min = 2, max = 25, message = "Seu sobrenome deve conter entre 2 e 25 caracteres.")
	private String surname;
	
	@Column(name = "usr_nickname", length = 25)
	@Length(max = 25, message = "Este campo deve conter no máximo 25 caracteres.")
	private String nickname;
	
	@Column(name = "usr_birth_date", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Campo obrigatório.")
	private Date birthDate;
	
	@Column(name = "usr_age", nullable = false)
	@Min(18)
	private int age;
	
	@ManyToOne
	@JoinColumn(name = "cnt_id")
	private Country country;
	
	@Column(name = "usr_email", length = 100, nullable = false)
	@NotEmpty(message = "Digite um email válido.")
	@NotNull(message = "Digite um email válido.")
	@Email(message = "Digite um email válido.")
	private String email;
	
	@Column(name = "usr_password", length = 150, nullable = false)
	private String password;
	
	@Column(name = "usr_role", length = 20, nullable = false)
	private String role;
	
	@OneToMany(mappedBy = "user")
	private List<Operation> operations;
	
	@OneToMany(mappedBy = "user")
	private List<Bank> bank;
	
	@OneToMany(mappedBy = "user")
	private List<Deposit> deposit;
	
	@OneToMany(mappedBy = "user")
	private List<Withdraw> withdraw;
	
	public Users() {
		this.birthDate = new Date();
		this.country = new Country();
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public List<Bank> getBank() {
		return bank;
	}

	public void setBank(List<Bank> bank) {
		this.bank = bank;
	}

	public List<Deposit> getDeposit() {
		return deposit;
	}

	public void setDeposit(List<Deposit> deposit) {
		this.deposit = deposit;
	}

	public List<Withdraw> getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(List<Withdraw> withdraw) {
		this.withdraw = withdraw;
	}
	
	public void calcAge(Date birthDate) {
		//Data de hoje
		GregorianCalendar agora = new GregorianCalendar();
		int ano = 0, mes = 0, dia = 0;
		
		//Data de nascimento
		GregorianCalendar nascimento = new GregorianCalendar();
		int anoNasc = 0, mesNasc = 0, diaNasc = 0;
		
		//Idade
		int idade = 0;
		
		if(this.getBirthDate() != null) {
			nascimento.setTime(this.getBirthDate());
			
			ano = agora.get(Calendar.YEAR);
			mes = agora.get(Calendar.MONTH);
			dia = agora.get(Calendar.DAY_OF_MONTH);
			
			anoNasc = nascimento.get(Calendar.YEAR);
			mesNasc = nascimento.get(Calendar.MONTH);
			diaNasc = nascimento.get(Calendar.DAY_OF_MONTH);
			
			idade = ano - anoNasc;
			
			//Calculando diferenças de mês e dia
			if(mes < mesNasc) {
				idade--;
			} else if(mes == mesNasc && dia < diaNasc) {
				idade--;
			}
			
			//Se resultar em idade negativa
			if(idade < 0) {
				idade = 0;
			}
			
			this.setAge(idade);
		}
	}

}
