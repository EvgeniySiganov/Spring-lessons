package ru.iteco.stocksevice.security.jwt;

public interface JwtProvider {
    String generateJwt(String username);
    boolean validateJwt(String jwt);
    String getUserNameFromJwt(String token);
}
