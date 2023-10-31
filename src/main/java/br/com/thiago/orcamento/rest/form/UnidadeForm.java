package br.com.thiago.orcamento.rest.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UnidadeForm {
    @NotEmpty(message = "O nome não pode ser vazio")
    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 255, message = "O nome pode ter no máximo 255 caracteres")
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;
}
