package ma.emsi.Patient.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password("{noop}azerty").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}azerty").roles("ADMIN","USER");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/save**/**","/delete**/**","/form**/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/patient**/**").hasRole("USER");
		http.exceptionHandling().accessDeniedPage("/notAuthorized");
		http.csrf();
	}

}
