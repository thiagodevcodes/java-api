package br.com.thiago.orcamento.rest.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.thiago.orcamento.rest.dto.ProgramaDto;
import br.com.thiago.orcamento.rest.form.ProgramaForm;
import br.com.thiago.orcamento.rest.form.ProgramaUpdateForm;
import br.com.thiago.orcamento.service.ProgramaService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;

@RestController
@RequestMapping("/programa")
public class ProgramaController {
    @Autowired
    ProgramaService programaService;

    @GetMapping
    public ResponseEntity<List<ProgramaDto>> findAll() {
        List<ProgramaDto> programaDtoList = programaService.findAll();
        return ResponseEntity.ok().body(programaDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramaDto> find(@PathVariable("id") Integer id) {
        ProgramaDto programaDto = programaService.findById(id);
        return ResponseEntity.ok().body(programaDto);
    }

    @PostMapping
    public ResponseEntity<ProgramaDto> insert(@Valid @RequestBody ProgramaForm programaForm, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }

        ProgramaDto programaDto = programaService.insert(programaForm);
        return ResponseEntity.ok().body(programaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramaDto> update(@Valid @RequestBody
        ProgramaUpdateForm programaUpdateForm, @PathVariable("id") Integer id, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }
        
        ProgramaDto programaDto = programaService.updateById(programaUpdateForm, id);
        return ResponseEntity.ok().body(programaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        programaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


