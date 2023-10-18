package br.com.thiago.orcamento.rest.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ModalidadeAplicacaoForm {
        @NotEmpty
        @NotBlank
        @Size(max = 100)
        private String nome;

        @NotNull
        private Float codigo;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDateTime dataCadastro;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDateTime dataAlteracao;
}
