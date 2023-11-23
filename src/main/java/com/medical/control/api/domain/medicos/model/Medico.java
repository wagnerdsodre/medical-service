package com.medical.control.api.domain.medicos.model;


import com.medical.control.api.domain.endereco.Endereco;
import com.medical.control.api.domain.medicos.dto.CadastroMedico;
import com.medical.control.api.domain.medicos.dto.DadosAtualizacaoMedico;
import com.medical.control.api.domain.medicos.enuns.Especialidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Medico(CadastroMedico dados) {
        this.nome=dados.nome();
        this.email=dados.email();
        this.telefone=dados.telefone();
        this.crm=dados.crm();
        this.especialidade=dados.especialidade();
        this.ativo = true;
        this.endereco=new Endereco(dados.endereco());
    }

    public void Updated(DadosAtualizacaoMedico dados) {

        if(dados.nome() != null){
            this.nome =dados.nome();
        }

        if(dados.telefone() != null){
            this.telefone =dados.telefone();
        }

        if(dados.endereco() != null){
            this.endereco.Atualizar(dados.endereco());
        }
    }
    public void inative() {
        this.ativo = false;
    }
}
