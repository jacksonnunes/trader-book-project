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
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ope_operation")
public class Operacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ope_data", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Campo obrigatório.")
	private Date data;
	
	@NotNull(message = "Escolher banca.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ban_id")
	private Banca bancaOperacao;
	
	@NotNull(message = "Preencher informações.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mkt_id")
	private Mercado mercado;
	
	@NotNull(message = "Preencher informações.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "str_id")
	private Estrategia estrategia;
	
	@NotNull(message = "Preencher informações.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "com_id")
	private Competicao competicao;
	
	@Column(name = "ope_evento", length = 100, nullable = false)
	@NotNull(message = "Preencher informações.")
	@Size(min = 5, max = 100, message = "Este campo deve conter entre 5 e 100 caracteres.")
	private String evento;
	
	@Column(name = "ope_valor_investido", precision = 2, nullable = false)
	@NotNull(message = "Preencher informações.")
	@Positive(message = "O valor deve ser positivo.")
	private double valorInvestido;
	
	@Column(name = "ope_valor_retorno", precision = 2)
	private double valorRetorno;
	
	@Column(name = "ope_roi", precision = 2)
	private double ROI;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Mercado getMercado() {
		return mercado;
	}

	public void setMercado(Mercado mercado) {
		this.mercado = mercado;
	}

	public Estrategia getEstrategia() {
		return estrategia;
	}

	public void setEstrategia(Estrategia estrategia) {
		this.estrategia = estrategia;
	}

	public Competicao getCompeticao() {
		return competicao;
	}

	public void setCompeticao(Competicao competicao) {
		this.competicao = competicao;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public double getValorInvestido() {
		return valorInvestido;
	}

	public void setValorInvestido(double valorInvestido) {
		this.valorInvestido = valorInvestido;
	}

	public double getValorRetorno() {
		return valorRetorno;
	}

	public void setValorRetorno(double valorRetorno) {
		this.valorRetorno = valorRetorno;
	}

	public double getROI() {
		return ROI;
	}

	public void setROI(double rOI) {
		ROI = rOI;
	}

}
