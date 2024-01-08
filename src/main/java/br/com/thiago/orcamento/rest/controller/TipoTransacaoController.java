package br.com.thiago.orcamento.rest.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.thiago.orcamento.rest.dto.TipoTransacaoDto;
import br.com.thiago.orcamento.rest.form.TipoTransacaoForm;
import br.com.thiago.orcamento.service.TipoTransacaoService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;

@RestController
@RequestMapping("/tipo-transacao")
public class TipoTransacaoController {

    @Autowired
    TipoTransacaoService tipoTransacaoService;

    @GetMapping
    public ResponseEntity<Page<TipoTransacaoDto>> findAll(Pageable page) {
        Page<TipoTransacaoDto> tipoTransacaoDtoPage = tipoTransacaoService.findAll(page);
        return ResponseEntity.ok().body(tipoTransacaoDtoPage);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TipoTransacaoDto>> findAllData() {
        List<TipoTransacaoDto> tipoTransacaoDtoList = tipoTransacaoService.findAllData();
        return ResponseEntity.ok().body(tipoTransacaoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoTransacaoDto> find(@PathVariable("id") Integer id) {
        TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.findById(id);
        return ResponseEntity.ok().body(tipoTransacaoDto);
    }

    @PostMapping
    public ResponseEntity<TipoTransacaoDto> insert(@Valid @RequestBody TipoTransacaoForm tipoTransacaoForm, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }

        TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.insert(tipoTransacaoForm);
        return ResponseEntity.ok().body(tipoTransacaoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoTransacaoDto> update(@Valid @RequestBody TipoTransacaoForm tipoTransacaoForm
            , @PathVariable("id") Integer id, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }

        TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.updateById(tipoTransacaoForm, id);
        return ResponseEntity.ok().body(tipoTransacaoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        tipoTransacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
