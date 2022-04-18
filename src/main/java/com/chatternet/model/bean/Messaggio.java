package com.chatternet.model.bean;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Messaggio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMessaggio")
	private int idMessaggio;
	
	@Column(name = "testo", length = 65535, columnDefinition="TEXT")
	private String testo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Column(name = "ora")
	private Date ora;
	
	@Column(name = "FKutente")
	private int FKutente;
	
	@Column(name = "FKchat")
	private int FKchat;
	
	public Messaggio() {
		
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

	public Date getOra() {
		return ora;
	}

	public void setOra(Date ora) {
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