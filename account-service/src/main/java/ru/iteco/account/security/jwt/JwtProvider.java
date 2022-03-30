package ru.iteco.account.security.jwt;

public interface JwtProvider {

    String generateJwt(String username);

    boolean validateJwt(String jwt);

    String getUsernameFromJwt(String token);
}
