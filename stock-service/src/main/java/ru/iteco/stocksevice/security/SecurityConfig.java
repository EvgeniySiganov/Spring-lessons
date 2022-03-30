package ru.iteco.stocksevice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("auth.audience")
    private String audience;

    @Value("spring.security.oauth2.resourceserver.jwt.issuer-uri")
    private String issuerUry;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/get-stock-quotes").hasAnyAuthority("SCOPE_Pro", "SCOPE_Base")
                .antMatchers("/get-historical-quotes").hasAuthority("SCOPE_Base")
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer();
        super.configure(http);
    }

    @Bean
    JwtDecoder jwtDecoder(){
        NimbusJwtDecoder nimbusJwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuerUry);
        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> issureValidator = JwtValidators.createDefaultWithIssuer(issuerUry);
        OAuth2TokenValidator<Jwt> delegateValidator = new DelegatingOAuth2TokenValidator<Jwt>(audienceValidator, issureValidator);
        nimbusJwtDecoder.setJwtValidator(delegateValidator);
        return nimbusJwtDecoder;
    }
}
