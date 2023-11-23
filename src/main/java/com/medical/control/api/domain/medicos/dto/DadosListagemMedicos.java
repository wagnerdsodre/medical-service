package com.medical.control.api.domain.medicos.dto;

import com.medical.control.api.domain.endereco.Endereco;
import com.medical.control.api.domain.medicos.enuns.Especialidade;
import com.medical.control.api.domain.medicos.model.Medico;

public record DadosListagemMedicos(Long id, String nome, String email, String telefone, String crm,
                                   Especialidade especialidade, Endereco endereco) {

  public DadosListagemMedicos(Medico medico) {
    this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(),
        medico.getEspecialidade(), medico.getEndereco());
  }
}
