package br.com.thiago.orcamento.rest.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UnidadeOrcamentariaForm {
    @NotBlank
    @NotEmpty
    @Size(max = 100)
    private String nome;

    @NotNull
    private Integer codigo;
}
