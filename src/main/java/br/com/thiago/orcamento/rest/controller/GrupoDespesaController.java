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

import br.com.thiago.orcamento.rest.dto.GrupoDespesaDto;
import br.com.thiago.orcamento.rest.form.GrupoDespesaForm;
import br.com.thiago.orcamento.service.GrupoDespesaService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;

@RestController
@RequestMapping("/grupo-despesa")
public class GrupoDespesaController {
    @Autowired
    GrupoDespesaService grupoDespesaService;

    @GetMapping
    public ResponseEntity<Page<GrupoDespesaDto>> findAll(Pageable page) {
        Page<GrupoDespesaDto> grupoDespesaDtoPage = grupoDespesaService.findAll(page);
        return ResponseEntity.ok().body(grupoDespesaDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoDespesaDto> find(@PathVariable("id") Integer id) {
        GrupoDespesaDto grupoDespesaDto = grupoDespesaService.findById(id);
        return ResponseEntity.ok().body(grupoDespesaDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GrupoDespesaDto>> findAllData() {
        List<GrupoDespesaDto> grupoDespesaDtoList = grupoDespesaService.findAllData();
        return ResponseEntity.ok().body(grupoDespesaDtoList);
    }

    @PostMapping
    public ResponseEntity<GrupoDespesaDto> insert(@Valid @RequestBody GrupoDespesaForm grupoDespesaForm, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }

        GrupoDespesaDto grupoDespesaDto = grupoDespesaService.insert(grupoDespesaForm);
        return ResponseEntity.ok().body(grupoDespesaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoDespesaDto> update(@Valid @RequestBody
        GrupoDespesaForm grupoDespesaForm, @PathVariable("id") Integer id, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }
        
        GrupoDespesaDto grupoDespesaDto = grupoDespesaService.updateById(grupoDespesaForm, id);
        return ResponseEntity.ok().body(grupoDespesaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        grupoDespesaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
