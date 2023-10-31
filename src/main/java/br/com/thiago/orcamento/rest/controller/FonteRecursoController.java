package br.com.thiago.orcamento.rest.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.thiago.orcamento.rest.dto.FonteRecursoDto;
import br.com.thiago.orcamento.rest.form.FonteRecursoForm;
import br.com.thiago.orcamento.rest.form.FonteRecursoUpdateForm;
import br.com.thiago.orcamento.service.FonteRecursoService;
import br.com.thiago.orcamento.service.exceptions.ConstraintException;

@RestController
@RequestMapping("/fonte-recurso")
public class FonteRecursoController {
    @Autowired
    FonteRecursoService fonteRecursoService;

    @GetMapping
    public ResponseEntity<List<FonteRecursoDto>> findAll() {
        List<FonteRecursoDto> fonteRecursoDtoList = fonteRecursoService.findAll();
        return ResponseEntity.ok().body(fonteRecursoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FonteRecursoDto> find(@PathVariable("id") Integer id) {
        FonteRecursoDto fonteRecursoDto = fonteRecursoService.findById(id);
        return ResponseEntity.ok().body(fonteRecursoDto);
    }

    @PostMapping
    public ResponseEntity<FonteRecursoDto> insert(@Valid @RequestBody FonteRecursoForm fonteRecursoForm, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }

        FonteRecursoDto fonteRecursoDto = fonteRecursoService.insert(fonteRecursoForm);
        return ResponseEntity.ok().body(fonteRecursoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FonteRecursoDto> update(@Valid @RequestBody
        FonteRecursoUpdateForm fonteRecursoUpdateForm, @PathVariable("id") Integer id, BindingResult br) {
        List<String> errors = new ArrayList<>();
        
        if (br.hasErrors()) {
            br.getAllErrors().forEach(e -> {
                errors.add(e.getDefaultMessage());
            });
            throw new ConstraintException("Erro de Validação", errors);
        }
        
        FonteRecursoDto fonteRecursoDto = fonteRecursoService.updateById(fonteRecursoUpdateForm, id);
        return ResponseEntity.ok().body(fonteRecursoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        fonteRecursoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


