package com.chatternet.model.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Transient;

public class UserDTO {
	
	private int id;
	
	private String name;
	
	private String surname;
	
	private String username;
	
	private String password;
	
	private String photo;
	
	private Date birthDate;
	
	private Object unreadReceivedMessages;
	
	private boolean blockedAccount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Object getUnreadReceivedMessages() {
		return unreadReceivedMessages;
	}

	public void setUnreadReceivedMessages(Object unreadReceivedMessages) {
		this.unreadReceivedMessages = unreadReceivedMessages;
	}

	public boolean isBlockedAccount() {
		return blockedAccount;
	}

	public void setBlockedAccount(boolean blockedAccount) {
		this.blockedAccount = blockedAccount;
	}

	@Transient
    public String getPhotoPath() {
        if (photo == null || id == 0) return null;
         
        return "/user-photos/" + id + "/" + photo;
    }

	@Transient
	public int getAgeFromBirthDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
		String birthDateString = dateFormat.format(this.birthDate);  
		LocalDate birthDate = LocalDate.parse(birthDateString);
		LocalDate currentDate = LocalDate.now(); 
		int age = Period.between(birthDate, currentDate).getYears();  
		return age;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(surname, other.surname) && Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(photo, other.photo) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username
				+ ", password=" + password + ", photo=" + photo + ", birthDate=" + birthDate
				+ ", unreadReceivedMessages=" + unreadReceivedMessages + ", blockedAccount=" + blockedAccount
				+ "]";
	}

}