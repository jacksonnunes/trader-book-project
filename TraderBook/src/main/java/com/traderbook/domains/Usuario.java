package com.traderbook.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id")
	private Long id;
	
	@Column(name = "usr_username", length = 25, nullable = false)
	@NotEmpty(message = "O preenchimento é obrigatório.")
	@NotNull(message = "O preenchimento é obrigatório.")
	@Length(min = 2, max = 25, message = "Seu nome deve conter entre 2 e 25 caracteres.")
	private String nome;
	
	@Column(name = "usr_surname", length = 25, nullable = false)
	@NotEmpty(message = "O preenchimento é obrigatório.")
	@NotNull(message = "O preenchimento é obrigatório.")
	@Length(min = 2, max = 25, message = "Seu sobrenome deve conter entre 2 e 25 caracteres.")
	private String sobrenome;
	
	@Column(name = "usr_nickname", length = 25)
	@Length(max = 25, message = "Este campo deve conter no máximo 25 caracteres.")
	private String apelido;
	
	@Column(name = "usr_data_nascimento", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Campo obrigatório.")
	private Date dataNascimento;
	
	@Column(name = "usr_age", nullable = false)
	@Min(18)
	private int idade;
	
	@Column(name = "usr_country", length = 25, nullable = false)
	@NotEmpty(message = "O preenchimento é obrigatório.")
	@NotNull(message = "O preenchimento é obrigatório.")
	@Length(min = 2, max = 25, message = "O campo deve conter entre 2 e 25 caracteres.")
	private String pais;
	
	@Column(name = "usr_email", length = 100, nullable = false)
	@NotEmpty(message = "Digite um email válido.")
	@NotNull(message = "Digite um email válido.")
	@Email(message = "Digite um email válido.")
	private String email;
	
	@Column(name = "usr_password", length = 150, nullable = false)
	private String password;
	
	@Column(name = "usr_role", length = 20, nullable = false)
	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
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

}
