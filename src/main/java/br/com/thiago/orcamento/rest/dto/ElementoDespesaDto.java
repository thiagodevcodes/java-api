package br.com.thiago.orcamento.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementoDespesaDto {
    private Integer id;
    private Integer codigo;
    private String nome;
}
