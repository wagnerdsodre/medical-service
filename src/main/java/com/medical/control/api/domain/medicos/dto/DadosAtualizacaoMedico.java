package com.medical.control.api.domain.medicos.dto;

import com.medical.control.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(@NotNull Long id, String nome, String telefone,
                                     DadosEndereco endereco) {


}
