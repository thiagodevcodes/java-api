package br.com.thiago.orcamento.rest.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.thiago.orcamento.rest.dto.ElementoDespesaDto;
import br.com.thiago.orcamento.rest.form.ElementoDespesaForm;
import br.com.thiago.orcamento.rest.form.ElementoDespesaUpdateForm;
import br.com.thiago.orcamento.service.ElementoDespesaService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;

@RestController
@RequestMapping("/elemento-despesa")
public class ElementoDespesaController {
    @Autowired
    ElementoDespesaService elementoDespesaService;

    @GetMapping
    public ResponseEntity<List<ElementoDespesaDto>> findAll() {
        List<ElementoDespesaDto> elementoDespesaDtoList = elementoDespesaService.findAll();
        return ResponseEntity.ok().body(elementoDespesaDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElementoDespesaDto> find(@PathVariable("id") Integer id) {
        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.findById(id);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

    @PostMapping
    public ResponseEntity<ElementoDespesaDto> insert(@Valid @RequestBody ElementoDespesaForm elementoDespesaForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.insert(elementoDespesaForm);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElementoDespesaDto> update(@Valid @RequestBody
        ElementoDespesaUpdateForm elementoDespesaUpdateForm, @PathVariable("id") Integer id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        
        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.updateById(elementoDespesaUpdateForm, id);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        elementoDespesaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


