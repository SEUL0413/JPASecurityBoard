package com.seul.security.jpa.config;

import com.seul.security.jpa.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity //@Configuration에 @EnableWebSecurity를 추가해 Spring Security 설정 클래스임을 알려준다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증을 미리 체크하기 위해 사용
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers( "/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { //HttpSecurity를 통해 HTTP 요청에 대한 보안을 구성할 수 있다.
        http
                .csrf().ignoringAntMatchers("/**") /* REST API 사용 예외처리 */
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll() //경로에 대해 권한 없이 접근이 가능하다.
                .and()
                .formLogin() //form 기반으로 인증한다. /login 경로로 접근하면, Spring Security에서 제공하는 로그인 Form을 사용할 수 있다
                .loginPage("/login") //기본으로 제공되는 form 말고, 커스텀 로그인 폼을 사용하기 위해 loginPage() 메소드를 사용했다.
                .loginProcessingUrl("/loginProc") //Security에서 해당 주소로 오는 요청을 낚아채서 수행한다.
                .defaultSuccessUrl("/") //로그인 성공 시 이동되는 페이지이다
                .and()
                .logout() //로그아웃을 지원하는 메소드이며, WebSecurityConfigureAdapter를 사용하면 자동으로 적용된다.
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/"); //로그아웃 성공시 이동되는 페이지이다.
    }

}
