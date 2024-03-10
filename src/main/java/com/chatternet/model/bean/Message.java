package com.chatternet.model.bean;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMessage")
	private int idMessage;
	
	@Column(name = "text", length = 65535, columnDefinition="TEXT")
	private String text;
	
	@Column(name = "time")
	private String time;
	
	@Column(name = "FKuser")
	private int FKuser;
	
	@Column(name = "FKchat")
	private int FKchat;
	
	@Column(name = "state")
	private MessageStatus state;
	
	public Message() {
		
	}

	public Message(String testo, String ora, int fKutente, int fKchat, MessageStatus stato) {
		this.text = testo;
		this.time = ora;
		FKuser = fKutente;
		FKchat = fKchat;
		this.state = stato;
	}

	public int getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getFKuser() {
		return FKuser;
	}

	public void setFKuser(int fKuser) {
		FKuser = fKuser;
	}

	public int getFKchat() {
		return FKchat;
	}

	public void setFKchat(int fKchat) {
		FKchat = fKchat;
	}

	public MessageStatus getState() {
		return state;
	}

	public void setState(MessageStatus state) {
		this.state = state;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return FKchat == other.FKchat && FKuser == other.FKuser && idMessage == other.idMessage
				&& Objects.equals(time, other.time) && Objects.equals(text, other.text);
	}

	@Override
	public String toString() {
		return "Message [idMessage=" + idMessage + ", text=" + text + ", time=" + time + ", FKuser=" + FKuser
				+ ", FKchat=" + FKchat + "]";
	}
}