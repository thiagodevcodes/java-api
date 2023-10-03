package br.com.thiago.orcamento.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class TipoLancamentoForm {

    @NotEmpty
    @NotBlank
    @Size(max = 100)
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAlteracao;
}
