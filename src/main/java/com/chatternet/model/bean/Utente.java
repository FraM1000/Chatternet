package com.chatternet.model.bean;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.sql.Blob;
import java.util.Date;

@Entity
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUtente")
	private int idUtente;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "sesso")
	private String sesso;
	
	@Column(name = "dataNascita")
	private Date dataNascita;
	
	@Column(name = "fotoProfilo")
	private Blob fotoProfilo;
	
	@Column(name = "FKcredenziale")
	private int FKcredenziale;

	public Utente() {
		
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Blob getFotoProfilo() {
		return fotoProfilo;
	}

	public void setFotoProfilo(Blob fotoProfilo) {
		this.fotoProfilo = fotoProfilo;
	}

	public int getFKcredenziale() {
		return FKcredenziale;
	}

	public void setFKcredenziale(int fKcredenziale) {
		FKcredenziale = fKcredenziale;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return FKcredenziale == other.FKcredenziale && Objects.equals(cognome, other.cognome)
				&& idUtente == other.idUtente && Objects.equals(nome, other.nome) && Objects.equals(sesso, other.sesso);
	}

	@Override
	public String toString() {
		return "Utente [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso
				+ ", FKcredenziale=" + FKcredenziale + "]";
	}
	
	

}
