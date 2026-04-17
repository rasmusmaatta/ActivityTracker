package fi.haagahelia.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
				authorize -> authorize
						.requestMatchers("/css/**").permitAll()
						.requestMatchers("/api/**").permitAll()
						.anyRequest().authenticated())
				.formLogin(formlogin -> formlogin.loginPage("/login")
						.defaultSuccessUrl("/ActivityList", true)
						.permitAll())
				.logout(logout -> logout.permitAll())
				.csrf(csrf -> csrf.disable());
		return http.build();
    }
}