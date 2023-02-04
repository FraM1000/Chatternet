package com.chatternet.model.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Transient;

public class UtenteDTO {
	
	private int id;
	
	private String nome;
	
	private String cognome;
	
	private String username;
	
	private String password;
	
	private String foto;
	
	private Date dataNascita;
	
	private Object messaggiRicevutiNonLetti;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	@Transient
    public String getFotoPath() {
        if (foto == null || id == 0) return null;
         
        return "/user-photos/" + id + "/" + foto;
    }

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Object getMessaggiRicevutiNonLetti() {
		return messaggiRicevutiNonLetti;
	}

	public void setMessaggiRicevutiNonLetti(Object numMessRicNonLet) {
		this.messaggiRicevutiNonLetti = numMessRicNonLet;
	}
	
	@Transient
	public int ottieniEtaDaDataNascita() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
		String dataNascitaString = dateFormat.format(dataNascita);  
		LocalDate dataNascita = LocalDate.parse(dataNascitaString);
		LocalDate dataAttuale = LocalDate.now(); 
		int eta = Period.between(dataNascita, dataAttuale).getYears();  
		return eta;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UtenteDTO other = (UtenteDTO) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(dataNascita, other.dataNascita)
				&& Objects.equals(foto, other.foto) && id == other.id && Objects.equals(nome, other.nome)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "UtenteDTO [id=" + id  + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", password=" + password
				+ ", foto=" + foto + ", dataNascita=" + dataNascita + "]";
	}

}
