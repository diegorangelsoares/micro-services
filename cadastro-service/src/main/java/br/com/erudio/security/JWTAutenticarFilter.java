package br.com.erudio.security;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    public static final String TOKEN_SENHA = "35de8308-282c-11ec-9621-0242ac130002";

    private static final int TOKEN_EXPIRACAO = 600000;

    public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


}
