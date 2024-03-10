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
	
	@Column(name = "FKfirstUser")
	private int FKfirstUser;
	
	@Column(name = "FKsecondUser")
	private int FKsecondUser;
	
	@Column(name = "lastTextDate")
	private String lastTextDate;

	public Chat() {
		
	}

	public int getIdChat() {
		return idChat;
	}

	public void setIdChat(int idChat) {
		this.idChat = idChat;
	}

	public int getFKfirstUser() {
		return FKfirstUser;
	}

	public void setFKfirstUser(int fKfirstUser) {
		FKfirstUser = fKfirstUser;
	}

	public int getFKsecondUser() {
		return FKsecondUser;
	}

	public void setFKsecondUser(int fKsecondUser) {
		FKsecondUser = fKsecondUser;
	}

	public String getLastTextDate() {
		return lastTextDate;
	}

	public void setLastTextDate(String lastTextDate) {
		this.lastTextDate = lastTextDate;
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
		return FKsecondUser == other.FKsecondUser && FKfirstUser == other.FKfirstUser
				&& Objects.equals(lastTextDate, other.lastTextDate) && idChat == other.idChat;
	}

	@Override
	public String toString() {
		return "Chat [idChat=" + idChat + ", FKfirstUser=" + FKfirstUser + ", FKsecondUser=" + FKsecondUser + ", lastTextDate=" + lastTextDate + "]";
	}
	
}
