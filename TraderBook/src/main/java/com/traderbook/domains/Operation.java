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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ope_operations")
public class Operation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ope_date", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Campo obrigatório.")
	private Date date;
	
	@NotNull(message = "Escolher banca.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ban_id")
	private Bank bank;
	
	@NotNull(message = "Escolher modalidade da aposta.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "asp_id")
	private Aspect aspect;
	
	@NotNull(message = "Escolher esporte.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "spo_id")
	private Sport sport;
	
	@NotNull(message = "Preencher informações.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mkt_id")
	private Market market;
	
	@NotNull(message = "Preencher informações.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "str_id")
	private Strategy strategy;
	
	@NotNull(message = "Preencher informações.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cnt_id")
	private Country country;
	
	@NotNull(message = "Preencher informações.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "com_id")
	private Competition competition;
	
	@Column(name = "ope_team_one", length = 100, nullable = false)
	@NotNull(message = "Preencher informações do time/jogador da casa.")
	@Size(min = 5, max = 35, message = "Este campo deve conter entre 5 e 35 caracteres.")
	private String teamOne;
	
	@Column(name = "ope_team_two", length = 100, nullable = false)
	@NotNull(message = "Preencher informações do time/jogador visitante.")
	@Size(min = 5, max = 35, message = "Este campo deve conter entre 5 e 35 caracteres.")
	private String teamTwo;
	
	@Column(name = "ope_invested_value", precision = 2, nullable = false)
	@NotNull(message = "Preencher informações do valor investido nesta operação.")
	@Min(value = 0, message = "O valor deve ser positivo.")
	private double investedValue;
	
	@Column(name = "ope_bank_value", precision = 2, nullable = false)
	private double bankValue;
	
	//Este valor é referente à porcentagem do valor da banca investido.
	@Column(name = "ope_bank_perc_invested", precision = 4, nullable = false)
	private double bankPercInvested;
	
	@Column(name = "ope_returned_value", precision = 2)
	private double returnedValue;
	
	@Column(name = "ope_roi", precision = 4)
	private double ROI;
	
	@Column(name = "ope_bank_roi", precision = 4)
	private double bankROI;
	
	//Este valor é repassado quando altera a operação, para atualização do saldo da banca
	@Column(name = "ope_previous_value", precision = 2)
	private double previousValue;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usr_id")
	private Users user;
	
	public Operation() {
		this.date = new Date();
		this.bank = new Bank();
		this.aspect = new Aspect();
		this.market = new Market();
		this.strategy = new Strategy();
		this.competition = new Competition();
		this.user = new Users();
		this.ROI = 0.0;
		this.bankROI = 0.0;
		this.previousValue = 0.0;
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

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Aspect getAspect() {
		return aspect;
	}

	public void setAspect(Aspect aspect) {
		this.aspect = aspect;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public String getTeamOne() {
		return teamOne;
	}

	public void setTeamOne(String teamOne) {
		this.teamOne = teamOne;
	}

	public String getTeamTwo() {
		return teamTwo;
	}

	public void setTeamTwo(String teamTwo) {
		this.teamTwo = teamTwo;
	}

	public double getInvestedValue() {
		return investedValue;
	}

	public void setInvestedValue(double investedValue) {
		this.investedValue = investedValue;
	}

	public double getBankValue() {
		return bankValue;
	}

	public void setBankValue(double bankValue) {
		this.bankValue = bankValue;
	}

	public double getBankPercInvested() {
		return bankPercInvested;
	}

	public void setBankPercInvested(double bankPercInvested) {
		this.bankPercInvested = bankPercInvested;
	}

	public double getReturnedValue() {
		return returnedValue;
	}

	public void setReturnedValue(double returnedValue) {
		this.returnedValue = returnedValue;
	}

	public double getROI() {
		return ROI;
	}

	public void setROI(double rOI) {
		ROI = rOI;
	}

	public double getBankROI() {
		return bankROI;
	}

	public void setBankROI(double bankROI) {
		this.bankROI = bankROI;
	}

	public double getPreviousValue() {
		return previousValue;
	}

	public void setPreviousValue(double previousValue) {
		this.previousValue = previousValue;
	}
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public void calcBankPercInvested() {
		this.bankPercInvested = this.investedValue / this.bankValue;
	}
	
	public void calcROI() {
		this.ROI = this.returnedValue / this.investedValue;
	}
	
	public void calcBankROI() {
		this.bankROI = this.returnedValue / this.bankValue;
	}

}
