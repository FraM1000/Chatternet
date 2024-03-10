package com.chatternet.model.bean;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUser")
	private int idUser;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "sex")
	private String sex;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "birthDate")
	private Date birthDate;
	
	@Column(name = "profilePhoto")
	private String profilePhoto;
	
	@Column(name = "state")
	private UserStatus state;
	
	@Column(name = "FKcredential")
	private int FKcredential;

	public User() {
		
	}

	public int getIdUser() {
		return idUser;
	}



	public void setIdUser(int idUser) {
		this.idUser = idUser;
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



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public Date getBirthDate() {
		return birthDate;
	}



	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}



	public String getProfilePhoto() {
		return profilePhoto;
	}



	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}



	public UserStatus getState() {
		return state;
	}



	public void setState(UserStatus state) {
		this.state = state;
	}



	public int getFKcredential() {
		return FKcredential;
	}



	public void setFKcredential(int fKcredential) {
		FKcredential = fKcredential;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return FKcredential == other.FKcredential && Objects.equals(surname, other.surname)
				&& idUser == other.idUser && Objects.equals(name, other.name) && Objects.equals(sex, other.sex);
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", name=" + name + ", surname=" + surname + ", sex=" + sex
				+ ", FKcredential=" + FKcredential + "]";
	}
	
	

}
