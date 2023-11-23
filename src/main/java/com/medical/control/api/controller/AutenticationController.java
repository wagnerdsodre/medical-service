package com.medical.control.api.controller;

import com.medical.control.api.domain.usuario.User;
import com.medical.control.api.domain.usuario.dto.DadosAutentication;
import com.medical.control.api.infra.security.TokenService;
import com.medical.control.api.infra.security.dto.DataTokenJWTDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/api/login")
public class AutenticationController {

  @Autowired
  private AuthenticationManager manager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity<Object> login(@RequestBody @Valid DadosAutentication dados) {
    var AuthToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.password());
    var authenticate = manager.authenticate(AuthToken);
    var token = tokenService.gerarToken((User) authenticate.getPrincipal());


    return ResponseEntity.ok(new DataTokenJWTDto(token));
  }


}
