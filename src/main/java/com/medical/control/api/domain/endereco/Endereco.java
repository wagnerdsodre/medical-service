package com.medical.control.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String complemento;
    private String numero;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(DadosEndereco dados) {
       this.logradouro=dados.logradouro();
       this.complemento= dados.complemento();
       this.numero=dados.numero();
       this.cidade=dados.cidade();
       this.uf=dados.uf();
       this.cep=dados.cep();
    }


    public void Atualizar(DadosEndereco endereco) {

        if(endereco.logradouro() != null) {
            this.logradouro=endereco.logradouro();
        }
        if(endereco.complemento() != null) {
            this.complemento=endereco.complemento();
        }
        if(endereco.numero() != null) {
            this.numero=endereco.numero();
        }
        if(endereco.cidade() != null) {
            this.cidade=endereco.cidade();
        }
        if(endereco.uf() != null) {
            this.uf=endereco.uf();
        }
        if(endereco.cep() != null) {
            this.cep=endereco.cep();
        }

    }
}
