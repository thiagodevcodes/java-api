package br.com.thiago.orcamento.rest.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProgramaForm {
        @NotEmpty(message = "O nome não pode ser vazio")
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 255, message = "O nome pode ter no máximo 255 caracteres")
        @NotNull(message = "O nome não pode ser nulo")
        private String nome;

        @NotNull(message = "O código não pode ser nulo")
        private Integer codigo;
}
