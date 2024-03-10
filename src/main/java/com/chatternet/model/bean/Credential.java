package com.chatternet.model.bean;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
public class Credential implements UserDetails {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "idCredential")
	private int idCredential;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "signupDate")
	private String signupDate;
	
	@Column(name = "blockedAccount")
	private String blockedAccount;
	
	public Credential() {
		
	}

	public int getIdCredential() {
		return idCredential;
	}



	public void setIdCredential(int idCredential) {
		this.idCredential = idCredential;
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



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public String getSignupDate() {
		return signupDate;
	}



	public void setSignupDate(String signupDate) {
		this.signupDate = signupDate;
	}



	public String getBlockedAccount() {
		return blockedAccount;
	}



	public void setBlockedAccount(String blockedAccount) {
		this.blockedAccount = blockedAccount;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credential other = (Credential) obj;
		return Objects.equals(signupDate, other.signupDate) && idCredential == other.idCredential
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Credential idCredential=" + idCredential + ", username=" + username + ", password=" + password
				+ ", role=" + role + ", signupDate=" + signupDate + ", blockedAccount=" + blockedAccount;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return blockedAccount.equals("N");
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
