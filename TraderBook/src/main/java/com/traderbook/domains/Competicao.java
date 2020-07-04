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
public class Competicao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "com_nome", length = 25, nullable = false)
	@NotEmpty(message = "O preenchimento é obrigatório.")
	@NotNull(message = "O preenchimento é obrigatório.")
	@Length(min = 3, max = 25, message = "O nome da competição deve conter entre 3 e 25 caracteres.")
	private String nome;
	
	@NotNull(message = "Escolher país.")
	@ManyToOne
	@JoinColumn(name = "cnt_id")
	private Pais pais;
	
	@OneToMany(mappedBy = "competicao", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Operacao> operacao;

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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Operacao> getOperacao() {
		return operacao;
	}

	public void setOperacao(List<Operacao> operacao) {
		this.operacao = operacao;
	}

}
