package com.chatternet.model.bean;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idChat")
	private int idChat;
	
	@Column(name = "FKutenteUno")
	private int FKutenteUno;
	
	@Column(name = "FKutenteDue")
	private int FKutenteDue;
	
	@Column(name = "dataUltimoMessaggio")
	private String dataUltimoMessaggio;

	public Chat() {
		
	}

	public int getIdChat() {
		return idChat;
	}

	public void setIdChat(int idChat) {
		this.idChat = idChat;
	}

	public int getFKutenteUno() {
		return FKutenteUno;
	}

	public void setFKutenteUno(int fKutenteUno) {
		FKutenteUno = fKutenteUno;
	}

	public int getFKutenteDue() {
		return FKutenteDue;
	}

	public void setFKutenteDue(int fKutenteDue) {
		FKutenteDue = fKutenteDue;
	}

	public String getDataUltimoMessaggio() {
		return dataUltimoMessaggio;
	}

	public void setDataUltimoMessaggio(String dataUltimoMessaggio) {
		this.dataUltimoMessaggio = dataUltimoMessaggio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chat other = (Chat) obj;
		return FKutenteDue == other.FKutenteDue && FKutenteUno == other.FKutenteUno
				&& Objects.equals(dataUltimoMessaggio, other.dataUltimoMessaggio) && idChat == other.idChat;
	}

	@Override
	public String toString() {
		return "Chat [idChat=" + idChat + ", FKutenteUno=" + FKutenteUno + ", FKutenteDue=" + FKutenteDue + ", dataUltimoMessaggio=" + dataUltimoMessaggio + "]";
	}
	
}
