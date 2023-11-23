package com.medical.control.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.medical.control.api.domain.usuario.User;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  @Value("api.token.secret")
  private String secret;

  public String gerarToken(User user){
  System.out.println("secret: "+secret);
    String token = null;
    try {

      var algoritimo = Algorithm.HMAC256("root");
       token = JWT.create()
          .withIssuer("api medical service")
           .withSubject(user.getLogin())

          .sign(algoritimo);
    } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token", exception) {
     };
    }
    return token;
  }

  private Instant DataExpirationToken(){
    return LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("-03:00"));
  }


  public String getSubjectUser(String tokenJWT){
    try {
      var algoritimo  = Algorithm.HMAC256("root");
      return JWT.require(algoritimo)
          .withIssuer("api medical service")
          .build()
          .verify(tokenJWT)
          .getSubject();

    } catch (JWTVerificationException exception){
      throw new RuntimeException("Token JWT inválido ou expirado");
    }
  }



}
