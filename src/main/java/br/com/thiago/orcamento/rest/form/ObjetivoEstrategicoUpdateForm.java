package br.com.thiago.orcamento.rest.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ObjetivoEstrategicoUpdateForm {

        @NotEmpty
        @NotBlank(message = "O Nome não pode estar em branco.")
        @Size(max = 100)
        private String nome;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDateTime dataAlteracao;
}
