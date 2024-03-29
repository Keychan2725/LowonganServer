package com.java.lowongan.lowongan_server.config;

import com.java.lowongan.lowongan_server.security.AccessDenied;
import com.java.lowongan.lowongan_server.security.AuthEntryPointJwt;
import com.java.lowongan.lowongan_server.security.AuthTokenFilter;
import com.java.lowongan.lowongan_server.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailService adminDetailsService;

    @Autowired
    private AccessDenied accessDeniedHandler;


    @Autowired
    AuthEntryPointJwt unauthorizedHandler;





    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }


    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(adminDetailsService).passwordEncoder(passwordEncoder());
    }



    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            // API controller
            "/api/user/**",
            "/api/user/all",
            "/api/user/upload-image/**",
            "/api/user/delete-image/**",
            "/api/login",
            "/api/identitasUsers/**",
            "/api/identitasUsers/all",
            "/api/identitasUsers/update-pekerjaan",
            "/api/identitasUsers/add/**",
            "/api/identitasUsers/edit/**",
            "/api/pegawai/**/rekrut",
            "/api/pekerjaan/**/rekrut",
            "/api/pekerjaan/**",
            "/api/pekerjaan/user/**",
            "/api/pekerjaan/**/uploadImage",
            "/api/pekerjaan/lamar",
            "/api/pekerjaan/all",
            "/api/pekerjaan/add",
            "/api/pekerjaan/lamar/*",
            "/api/pekerjaan/pelamar/*",
            "/api/pekerjaan/getBy/**",
            "/api/pekerjaan/pelamar/**",
            "/api/pekerjaan/user/**/id//**",
            "/api/pekerjaan/user/**",
            "/api/pelamar/add",
            "/api/pelamar/edit/**",
            "/api/pelamar/terima/**",
            "/api/pelamar/tolak/**",
            "/api/pelamar/batal/**",
            "/api/pelamar/all",
            "/api/pelamar/getBy/**",
            "/api/pelamar/user/**",
            "/api/pelamar/byId/**",
            "/api/pekerjaan/**/pelamar/**",
            "/api/pelamar/**"
    };



    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}