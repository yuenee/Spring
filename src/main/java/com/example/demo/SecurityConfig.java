package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	データソース
	@Autowired
	private DataSource dataSource;
	
//	ユーザーIDとパスワードを取得するSQL分
	private static final String USER_SQL = "SELECT"
		+ " user_id,"
		+ " password,"
		+ " true"
		+ " FROM"
		+ " m_user"
		+ " WHERE"
		+ " user_id = ?";
	
//	ユーザーのロールを取得するSQL文
	private static final String ROLE_SQL = "SELECT"
		+ " user_id,"
		+ " role"
		+ " FROM"
		+ " m_user"
		+ " WHERE"
		+ " user_id = ?";
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
//		静的リソースへのアクセスには、セキュリティを適用しない
		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		ログイン不要ページの設定
		http
			.authorizeRequests()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/signup").permitAll()
				.anyRequest().authenticated();
		
//		ログイン処理
		http
			.formLogin()
				.loginProcessingUrl("/login")
				.loginPage("/login")
				.failureUrl("/login")
				.usernameParameter("userId")
				.passwordParameter("password")
				.defaultSuccessUrl("/home", true);
		
//		CSRF対策を無効に設定（一時的）
		http.csrf().disable();
		}
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
//		ログイン処理時のユーザー情報を、DBから取得する
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(USER_SQL)
			.authoritiesByUsernameQuery(ROLE_SQL)
			.passwordEncoder(passwordEncoder());
	}
}