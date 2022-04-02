package com.chatternet.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UtenteChat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUC")
	private int idUtenteChat;
	
	@Column(name = "FKutente")
	private int FKutente;
	
	@Column(name = "FKchat")
	private int FKchat;

	public UtenteChat() {
		
	}

	public int getIdUtenteChat() {
		return idUtenteChat;
	}

	public void setIdUtenteChat(int idUtenteChat) {
		this.idUtenteChat = idUtenteChat;
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
		UtenteChat other = (UtenteChat) obj;
		return FKchat == other.FKchat && FKutente == other.FKutente && idUtenteChat == other.idUtenteChat;
	}
	
}
