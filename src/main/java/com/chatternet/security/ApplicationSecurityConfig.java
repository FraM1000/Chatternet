package com.chatternet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.chatternet.controller.service.AppUserService;



@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private final AppUserService appUserService;
	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder; 
	@Autowired
	private SuccessHandler successHandler;
	@Autowired
	private FailureHandler failureHandler;
	@Autowired
	private CustomLogoutHandler logoutHandler;
 

	public ApplicationSecurityConfig(AppUserService appUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.appUserService = appUserService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		
	}

	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.authorizeRequests()
		.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
		.antMatchers("/registraUtente").anonymous()
		.antMatchers("/","/login","/registrazione","/registraUtente","/Icona", "/css/**","/images/**","/js/**","/static/**").permitAll()
		.antMatchers("/paginaChat").hasAuthority("USER")
		.antMatchers("/mostraChat").hasAuthority("USER")
		.antMatchers("/chat").hasAuthority("USER")
		.antMatchers("/eliminaChat").hasAuthority("USER")
		.antMatchers("/ricerca").hasAuthority("USER")
		.antMatchers("/cercaUtente").hasAuthority("USER")
		.antMatchers("/ricercaUtentePerId").hasAuthority("USER")
		.antMatchers("/paginaProfilo").hasAuthority("USER")
		.antMatchers("/inserisciFoto").hasAuthority("USER")
		.antMatchers("/eliminaFoto").hasAuthority("USER")
		.antMatchers("/modificaPassword").hasAuthority("USER")
		.antMatchers("/admin/**").hasAuthority("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login").permitAll().usernameParameter("user").passwordParameter("pass")
		.successHandler(successHandler)
		.failureHandler(failureHandler)
		.and()
		.rememberMe().tokenValiditySeconds(86400)
		.key("uniqueAndSecret").rememberMeParameter("remember-me")
		.userDetailsService(appUserService)
		.and()
		.logout().logoutUrl("/logout").addLogoutHandler(logoutHandler)
		.clearAuthentication(true)
		.invalidateHttpSession(true)
	    .deleteCookies("JSESSIONID", "remember-me")
		.logoutSuccessUrl("/login");
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(appUserService);
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		return provider;
		
	}
}
