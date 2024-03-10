package com.chatternet.model.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "theMessage")
public class MessageDTO {
	
	@JsonProperty("text")
	private String text;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonProperty("time")
	private LocalDateTime time;
	
	@JsonProperty("sender")
	private int sender;
	
	@JsonProperty("receiver")
	private int receiver;
	
	public MessageDTO() {
		
	}

	@JsonCreator
	public MessageDTO(@JsonProperty("text") String text, 
			@JsonProperty("time") LocalDateTime time, 
			@JsonProperty("sender") int sender,
			@JsonProperty("receiver") int receiver) {
		this.text = text;
		this.time = time;
		this.sender = sender;
		this.receiver = receiver; 
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageDTO other = (MessageDTO) obj;
		return Objects.equals(time, other.time) && Objects.equals(text, other.text)
				&& sender == other.sender && receiver == other.receiver;
	}

	@Override
	public String toString() {
		return "MessageDTO [text=" + text + ", time=" + time + ", sender=" + sender + ", receiver=" + receiver +"]";
	}

}
