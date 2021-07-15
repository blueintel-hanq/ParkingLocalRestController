package com.blueparking.rest.config.oauth;

import com.blueparking.rest.common.domain.users.define.UserRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@RequiredArgsConstructor
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer configurer) throws Exception {
        configurer.resourceId("SpringBoot");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests()
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**").permitAll()
                //.antMatchers("/api/towing/**").permitAll()
                //.antMatchers("/api/admin/**").hasRole(UserRoles.ADMIN.name())
                //.antMatchers(HttpMethod.POST, "/api/members/join").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
