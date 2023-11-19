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

import br.com.thiago.orcamento.rest.dto.SolicitanteDto;
import br.com.thiago.orcamento.rest.form.SolicitanteForm;
import br.com.thiago.orcamento.service.SolicitanteService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;

@RestController
@RequestMapping("/solicitante")
public class SolicitanteController {

    @Autowired
    SolicitanteService solicitanteService;

    @GetMapping
    public ResponseEntity<Page<SolicitanteDto>> findAll(Pageable page) {
        Page<SolicitanteDto> solicitanteDtoList = solicitanteService.findAll(page);
        return ResponseEntity.ok().body(solicitanteDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitanteDto> find(@PathVariable("id") Integer id) {
        SolicitanteDto solicitanteDto = solicitanteService.findById(id);
        return ResponseEntity.ok().body(solicitanteDto);
    }

    @PostMapping
    public ResponseEntity<SolicitanteDto> insert(@Valid @RequestBody SolicitanteForm solicitanteForm, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }

        SolicitanteDto solicitanteDto = solicitanteService.insert(solicitanteForm);
        return ResponseEntity.ok().body(solicitanteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitanteDto> update(@Valid @RequestBody SolicitanteForm solicitanteForm
            , @PathVariable("id") Integer id, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }

        SolicitanteDto solicitanteDto = solicitanteService.updateById(solicitanteForm, id);
        return ResponseEntity.ok().body(solicitanteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        solicitanteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
