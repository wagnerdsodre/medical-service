package com.medical.control.api.controller;

import com.medical.control.api.domain.medicos.MedicoRepository;
import com.medical.control.api.domain.medicos.dto.CadastroMedico;
import com.medical.control.api.domain.medicos.dto.DadosAtualizacaoMedico;
import com.medical.control.api.domain.medicos.dto.DadosListagemMedicos;
import com.medical.control.api.domain.medicos.dto.DetalhesMedico;
import com.medical.control.api.domain.medicos.model.Medico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/v1/api/doctors")
public class medicosController {

  @Autowired
  private MedicoRepository repository;

  @PostMapping
  @Transactional
  public ResponseEntity<DetalhesMedico> PostDoctor(@RequestBody @Valid CadastroMedico dados) {
    Medico medico = repository.save(new Medico(dados));
    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
        .buildAndExpand(medico.getId()).toUri();
    return ResponseEntity.created(uri).body(new DetalhesMedico(medico));
  }

  @GetMapping
  public ResponseEntity<Page<DadosListagemMedicos>> ListAllDoctors(
      @PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
    Page<DadosListagemMedicos> litagemMedicos = repository.findAllByAtivoTrue(pageable)
        .map(DadosListagemMedicos::new);
    return ResponseEntity.ok(litagemMedicos);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<DetalhesMedico> ListDoctorsForId(@PathVariable Long id) {
    Medico medico = repository.getReferenceById(id);
    return ResponseEntity.ok().body(new DetalhesMedico(medico));
  }

  @PutMapping()
  @Transactional
  public ResponseEntity<DetalhesMedico> UpdateDoctor(@RequestBody DadosAtualizacaoMedico dados) {
    Medico medico = repository.getReferenceById(dados.id());
    medico.Updated(dados);
    return ResponseEntity.ok(new DetalhesMedico(medico));
  }


  @DeleteMapping(value = "/{id}")
  @Transactional
  public ResponseEntity<Medico> DeleteDoctor(@PathVariable Long id) {
    Medico medico = repository.getReferenceById(id);
    medico.inative();
    return ResponseEntity.noContent().build();
  }


}
