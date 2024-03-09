package com.chatternet.model.bean;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Messaggio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMessage")
	private int idMessaggio;
	
	@Column(name = "text", length = 65535, columnDefinition="TEXT")
	private String testo;
	
	@Column(name = "time")
	private String ora;
	
	@Column(name = "FKuser")
	private int FKutente;
	
	@Column(name = "FKchat")
	private int FKchat;
	
	@Column(name = "state")
	private MessageStatus stato;
	
	public Messaggio() {
		
	}

	public Messaggio(String testo, String ora, int fKutente, int fKchat, MessageStatus stato) {
		this.testo = testo;
		this.ora = ora;
		FKutente = fKutente;
		FKchat = fKchat;
		this.stato = stato;
	}

	public int getIdMessaggio() {
		return idMessaggio;
	}

	public void setIdMessaggio(int idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getOra() {
		return ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}

	public int getFKutente() {
		return FKutente;
	}

	public void setFKutente(int fKutente) {
		FKutente = fKutente;
	}

	public int getFKchat() {
		return FKchat;
	}

	public void setFKchat(int fKchat) {
		FKchat = fKchat;
	}

	public MessageStatus getStato() {
		return stato;
	}

	public void setStato(MessageStatus stato) {
		this.stato = stato;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Messaggio other = (Messaggio) obj;
		return FKchat == other.FKchat && FKutente == other.FKutente && idMessaggio == other.idMessaggio
				&& Objects.equals(ora, other.ora) && Objects.equals(testo, other.testo);
	}

	@Override
	public String toString() {
		return "Messaggio [idMessaggio=" + idMessaggio + ", testo=" + testo + ", ora=" + ora + ", FKutente=" + FKutente
				+ ", FKchat=" + FKchat + "]";
	}
}