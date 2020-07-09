package classes.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import classes.config.CustomAuthenticationSuccessHandler;

import classes.service.UserService;
import org.springframework.beans.factory.annotation.Value;
@Configuration
@EnableWebSecurity
@EnableWebMvc
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @Autowired
	 * 
	 * @Qualifier("userDetailsService") UserDetailsService userDetailsService;
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder auth)
	 * throws Exception {
	 * 
	 * // add our users for in memory authentication
	 * 
	 * UserBuilder users = User.withDefaultPasswordEncoder();
	 * 
	 * auth.inMemoryAuthentication()
	 * .withUser(users.username("john").password("test123").roles("COMMON"))
	 * .withUser(users.username("mary").password("test123").roles("COMMON",
	 * "TEACHER"))
	 * .withUser(users.username("susan").password("test123").roles("COMMON",
	 * "ADMIN"));
	 * 
	 * auth.userDetailsService(userDetailsService).passwordEncoder(
	 * passwordEncoder());
	 * 
	 * }
	 */

	// add a reference to our security data source
	@Autowired
	private UserService userService;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	static SessionRegistry SR;
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/home/**").hasRole("COMMON")
				.antMatchers("/teachers/**").hasRole("TEACHER").antMatchers("/admins/**").hasRole("ADMIN")
				.antMatchers("/students/**").hasRole("STUDENT").antMatchers("/studentwrapper/myExamResultsList")
				.hasRole("STUDENT").antMatchers("/studentwrapper/showMyExamResult").hasRole("STUDENT")
				.antMatchers("/studentwrapper/video").hasRole("STUDENT").antMatchers("/studentwrapper/showVideo")
				.hasRole("STUDENT").antMatchers("/studentwrapper/**").hasAnyRole("TEACHER", "ADMIN")
				.antMatchers("/subjectwrapper/**").hasAnyRole("TEACHER", "ADMIN").antMatchers("/standard/**")
				.hasAnyRole("TEACHER", "ADMIN").antMatchers("/presentyrecordwrapper/**").hasRole("TEACHER")
				.antMatchers("/teacherwrapper/mySubjectList").hasRole("TEACHER")
				.antMatchers("/teacherwrapper/manageExamResult").hasRole("TEACHER")
				.antMatchers("/teacherwrapper/listSubjectVidoes").hasRole("TEACHER")
				.antMatchers("/teacherwrapper/showFormForVideoAdd").hasRole("TEACHER")
				.antMatchers("/teacherwrapper/showFormForVideoUpdate").hasRole("TEACHER")
				.antMatchers("/teacherwrapper/deleteVideo").hasRole("TEACHER")
				.antMatchers("/teacherwrapper/saveVideoInfo").hasRole("TEACHER")
				.antMatchers("/teacherwrapper/createExamResult").hasRole("TEACHER")
				.antMatchers("/teacherwrapper/deleteExamResult").hasRole("TEACHER")
				.antMatchers("/teacherwrapper/listExamResult").hasRole("TEACHER")
				.antMatchers("/teacherwrapper/showExamResultFormForUpdate").hasRole("TEACHER")
				.antMatchers("/teacherwrapper/saveExamResult").hasRole("TEACHER")
				.antMatchers("/teacherwrapper/deleteExamResult").hasRole("TEACHER").antMatchers("/teacherwrapper/**")
				.hasRole("ADMIN").antMatchers("/coursewrapper/**").hasRole("ADMIN")
				.antMatchers("/feeinstallmentwrapper/**").hasRole("ADMIN").antMatchers("/exam/**").hasRole("TEACHER")
				.and().formLogin().loginPage("/showMyLoginPage").loginProcessingUrl("/authenticateTheUser").permitAll()
				.and().logout().logoutSuccessUrl("/") // after logout redirect
														// to landing page
														// (root)
				.permitAll().and().exceptionHandling().accessDeniedPage("/access-denied").
				and().sessionManagement()
				.maximumSessions(1)
				.sessionRegistry(SR);
				

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); // set the custom user details
													// service
		auth.setPasswordEncoder(passwordEncoder()); // set the password encoder
													// - bcrypt
		return auth;
	}

}
