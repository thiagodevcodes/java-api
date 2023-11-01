package br.com.thiago.orcamento.rest.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.thiago.orcamento.rest.dto.LancamentoDto;
import br.com.thiago.orcamento.rest.form.LancamentoForm;
import br.com.thiago.orcamento.rest.form.LancamentoUpdateForm;
import br.com.thiago.orcamento.service.LancamentoService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController {

    @Autowired
    LancamentoService lancamentoService;

    @GetMapping
    public ResponseEntity<List<LancamentoDto>> findAll() {
        List<LancamentoDto> lancamentoDtoList = lancamentoService.findAll();
        return ResponseEntity.ok().body(lancamentoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDto> find(@PathVariable("id") Integer id) {
        LancamentoDto lancamentoDto = lancamentoService.findById(id);
        return ResponseEntity.ok().body(lancamentoDto);
    }

    @PostMapping
    public ResponseEntity<LancamentoDto> insert(@Valid @RequestBody LancamentoForm lancamentoForm, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }

        LancamentoDto lancamentoDto = lancamentoService.insert(lancamentoForm);
        return ResponseEntity.ok().body(lancamentoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancamentoDto> update(@Valid @RequestBody LancamentoUpdateForm lancamentoUpdateForm
            , @PathVariable("id") Integer id, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }

        LancamentoDto lancamentoDto = lancamentoService.updateById(lancamentoUpdateForm, id);
        return ResponseEntity.ok().body(lancamentoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        lancamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
