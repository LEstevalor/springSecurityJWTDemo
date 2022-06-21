package com.it.config;


import com.it.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 密码校验
     * 创建BCryptPasswordEncoder注入容器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭csrf(前后端分离项目要关闭此功能）
                .csrf().disable()
                // 禁用session (前后端分离项目，不通过Session获取SecurityContext)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 请求认证配置
                .authorizeRequests()
                // 允许匿名访问：未登录可以访问，已登录不能访问
                .antMatchers("/xx").permitAll()           //路径“/xx”都能访问
                .antMatchers("/user/login").anonymous()   //匿名访问
                // .antMatchers("/login").permitAll()// 登录或未登录都能访问
                // .antMatchers("/textMybatis").hasAuthority("system:dept:list22")
                // 任意用户，认证之后才可以访问（除上面外的）
                .anyRequest().authenticated();

        //配置过滤器jwtAuthenticationTokenFilter在UsernamePasswordAuthenticationFilter之前
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }


}
