package br.com.thiago.orcamento.rest.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AcaoUpdateForm {
        @NotEmpty(message = "O nome não pode estar vazio")
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 255, message = "O nome precisa ser no máximo 255 caracteres")
        private String nome;

        @NotNull(message = "O código não pode ser nulo")
        private Integer codigo;
}
