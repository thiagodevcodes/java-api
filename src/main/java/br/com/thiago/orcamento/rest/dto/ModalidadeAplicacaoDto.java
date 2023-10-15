package br.com.thiago.orcamento.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModalidadeAplicacaoDto {
    private Integer id;
    private Float codigo;
    private String nome;
}
