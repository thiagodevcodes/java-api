package br.com.thiago.orcamento.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
public class TipoLancamentoUpdateForm {

    @NotEmpty
    @NotBlank(message = "O Nome não pode estar em branco.")
    @Size(max = 100)
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAlteracao;
}
