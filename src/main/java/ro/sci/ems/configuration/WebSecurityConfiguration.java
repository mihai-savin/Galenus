package ro.sci.ems.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("UserDetailsServiceImpl")
//	private UserRepository userRepository;
	private UserDetailsService userService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		 http.csrf().disable();
		http
		.authorizeRequests()
		.antMatchers("/", "/home", "/css/**", "/scss/**", "/images/**", "/js/**", "/webjars/**", "/register", "/rest/register").permitAll()
		.antMatchers("/doctor").hasRole("DOCTOR")
		.anyRequest().authenticated()
		.and()
		.formLogin()
				.loginPage("/login").permitAll()
				.and().logout().permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		auth.userDetailsService(userService);
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.userDetailsService(new UserDetailsService() {
//				@Override
//				public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//					return userRepository.findOne(username) ;
//				}
//			});
//	}
}