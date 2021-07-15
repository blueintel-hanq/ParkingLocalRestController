package com.blueparking.rest.config.oauth;

import com.blueparking.rest.common.domain.users.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;

@RequiredArgsConstructor
@EnableAuthorizationServer
@Configuration
@Slf4j
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final DataSource dataSource;
    private final UsersService usersService;
    private final TokenInfo tokenInfo;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {


        security.passwordEncoder(passwordEncoder);

        // 엔드포인트에 필터를 추가해줘야 .. 토큰 발급시 cors 문제가 발생하지 않는다.
        CorsFilter filter = new CorsFilter(corsConfigurationSource());
        security.addTokenEndpointAuthenticationFilter(filter);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer) {

        endpointsConfigurer
                .authenticationManager(authenticationManager)
                .userDetailsService(usersService)
                .tokenStore(tokenStore())
                .accessTokenConverter(jwtAccessTokenConverter());

    }

    @Bean
    public TokenStore tokenStore() {

        return new JwtTokenStore(jwtAccessTokenConverter());
        //return new JdbcTokenStore(dataSource);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        converter.setSigningKey(tokenInfo.getJwtKey());

        return converter;

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("*");                         // 서비스 도메인
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);

        return source;

    }
}
