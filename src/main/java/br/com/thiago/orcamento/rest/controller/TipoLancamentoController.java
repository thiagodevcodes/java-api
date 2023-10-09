package br.com.thiago.orcamento.rest.controller;

import br.com.thiago.orcamento.rest.dto.TipoLancamentoDto;
import br.com.thiago.orcamento.rest.form.TipoLancamentoForm;
import br.com.thiago.orcamento.rest.form.TipoLancamentoUpdateForm;
import br.com.thiago.orcamento.service.TipoLancamentoService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tipo-lancamento")
public class TipoLancamentoController {

    @Autowired
    TipoLancamentoService tipoLancamentoService;

    @GetMapping
    public ResponseEntity<List<TipoLancamentoDto>> findAll() {
        List<TipoLancamentoDto> tipoLancamentoDtoList = tipoLancamentoService.findAll();
        return ResponseEntity.ok().body(tipoLancamentoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoLancamentoDto> find(@PathVariable("id") Integer id) {
        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.findById(id);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

    @PostMapping
    public ResponseEntity<TipoLancamentoDto> insert(@Valid @RequestBody TipoLancamentoForm tipoLancamentoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        tipoLancamentoForm.setDataCadastro(LocalDateTime.now().minusHours(3));
        tipoLancamentoForm.setDataAlteracao(LocalDateTime.now().minusHours(3));
        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.insert(tipoLancamentoForm);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoLancamentoDto> update(@Valid @RequestBody TipoLancamentoUpdateForm tipoLancamentoUpdateForm
            , @PathVariable("id") Integer id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        tipoLancamentoUpdateForm.setDataAlteracao(LocalDateTime.now().minusHours(3));
        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.updateById(tipoLancamentoUpdateForm, id);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        tipoLancamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
