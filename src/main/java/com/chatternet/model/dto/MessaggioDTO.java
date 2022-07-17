package com.chatternet.model.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "ilMessaggio")
public class MessaggioDTO {
	
	@JsonProperty("testo")
	private String testo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonProperty("ora")
	private LocalDateTime ora;
	
	@JsonProperty("utenteInviante")
	private int utenteInviante;
	
	@JsonProperty("utenteRicevente")
	private int utenteRicevente;
	
	public MessaggioDTO() {
		
	}

	@JsonCreator
	public MessaggioDTO(@JsonProperty("testo") String testo, 
			@JsonProperty("ora") LocalDateTime ora, 
			@JsonProperty("utenteInviante") int utenteInviante,
			@JsonProperty("utenteRicevente") int utenteRicevente) {
		this.testo = testo;
		this.ora = ora;
		this.utenteInviante = utenteInviante;
		this.utenteRicevente = utenteRicevente; 
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public LocalDateTime getOra() {
		return ora;
	}

	public void setOra(LocalDateTime ora) {
		this.ora = ora;
	}

	public int getUtenteInviante() {
		return utenteInviante;
	}

	public void setUtenteInviante(int utenteInviante) {
		this.utenteInviante = utenteInviante;
	}

	public int getUtenteRicevente() {
		return utenteRicevente;
	}

	public void setUtenteRicevente(int utenteRicevente) {
		this.utenteRicevente = utenteRicevente;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessaggioDTO other = (MessaggioDTO) obj;
		return Objects.equals(ora, other.ora) && Objects.equals(testo, other.testo)
				&& utenteInviante == other.utenteInviante && utenteRicevente == other.utenteRicevente;
	}

	@Override
	public String toString() {
		return "MessaggioDTO [testo=" + testo + ", ora=" + ora + ", utenteInviante=" + utenteInviante + ", utenteRicevente=" + utenteRicevente +"]";
	}

}
