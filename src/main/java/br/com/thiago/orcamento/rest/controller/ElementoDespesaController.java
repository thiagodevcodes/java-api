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

import br.com.thiago.orcamento.rest.dto.ElementoDespesaDto;
import br.com.thiago.orcamento.rest.form.ElementoDespesaForm;
import br.com.thiago.orcamento.service.ElementoDespesaService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;

@RestController
@RequestMapping("/elemento-despesa")
public class ElementoDespesaController {
    @Autowired
    ElementoDespesaService elementoDespesaService;

    @GetMapping
    public ResponseEntity<Page<ElementoDespesaDto>> findAll(Pageable page) {
        Page<ElementoDespesaDto> elementoDespesaDtoPage = elementoDespesaService.findAll(page);
        return ResponseEntity.ok().body(elementoDespesaDtoPage);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ElementoDespesaDto>> findAllData() {
        List<ElementoDespesaDto> elementoDespesaDtoList = elementoDespesaService.findAllData();
        return ResponseEntity.ok().body(elementoDespesaDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElementoDespesaDto> find(@PathVariable("id") Integer id) {
        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.findById(id);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

    @PostMapping
    public ResponseEntity<ElementoDespesaDto> insert(@Valid @RequestBody ElementoDespesaForm elementoDespesaForm, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }

        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.insert(elementoDespesaForm);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElementoDespesaDto> update(@Valid @RequestBody
        ElementoDespesaForm elementoDespesaForm, @PathVariable("id") Integer id, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }
        
        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.updateById(elementoDespesaForm, id);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        elementoDespesaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


