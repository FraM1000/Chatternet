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
public class Credenziale implements UserDetails {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "idCredenziale")
	private int idCredenziale;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "ruolo")
	private String ruolo;
	
	@Column(name = "dataRegistrazione")
	private String dataRegistrazione;
	
	@Column(name = "accountBloccato")
	private String accountBloccato;
	
	public Credenziale() {
		
	}

	public int getIdCredenziale() {
		return idCredenziale;
	}

	public void setIdCredenziale(int idCredenziale) {
		this.idCredenziale = idCredenziale;
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

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(String dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}
	
	public String getAccountBloccato() {
		return accountBloccato;
	}

	public void setAccountBloccato(String accountBloccato) {
		this.accountBloccato = accountBloccato;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credenziale other = (Credenziale) obj;
		return Objects.equals(dataRegistrazione, other.dataRegistrazione) && idCredenziale == other.idCredenziale
				&& Objects.equals(password, other.password) && Objects.equals(ruolo, other.ruolo)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Credenziale idCredenziale=" + idCredenziale + ", username=" + username + ", password=" + password
				+ ", ruolo=" + ruolo + ", dataRegistrazione=" + dataRegistrazione + ", accountBloccato=" + accountBloccato;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(ruolo));
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountBloccato.equals("N");
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
